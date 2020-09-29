from flask import Flask
from flask_socketio import SocketIO, emit
from flask_cors import CORS
import cv2
import numpy as np
import matplotlib.pyplot as plt
from PIL import Image
import torch
import torch.nn as nn
import torch.nn.functional as F
import os
from torch.autograd import Variable
import seaborn as sns

import transforms as transforms
from skimage import io
from skimage.transform import resize
from models import *

app = Flask(__name__)
CORS(app, resources={r"/*": {"origins": "*"}})

app.config['SECRET_KEY'] = 'secret_key'
socketio = SocketIO(app, cors_allowed_origins="*")

@socketio.on('connect')
def connect():
    print('connect')

@socketio.on('disconnect')
def disconnect():
    print('client disconnect')

@socketio.on('cvdata')
def cvdata(data):

    data = data['data']
    length = len(data.keys())
    data = np.array([data[str(index)] for index in range(length)])
    data = data.reshape(320,240,4)

    gray = rgb2gray(data)

    gray = resize(gray, (48,48), mode='symmetric').astype(np.uint8)

    img = gray[:, :, np.newaxis]

    img = np.concatenate((img, img, img), axis=2)
    img = Image.fromarray(img)
    inputs = transform_test(img)

    class_names = ['Angry', 'Disgust', 'Fear', 'Happy', 'Sad', 'Surprise', 'Neutral']

    ncrops, c, h, w = np.shape(inputs)

    inputs = inputs.view(-1, c, h, w)
    inputs = Variable(inputs, volatile=True)
    outputs = net(inputs)

    outputs_avg = outputs.view(ncrops, -1).mean(0)  # avg over crops

    score = F.softmax(outputs_avg)
    _, predicted = torch.max(outputs_avg.data, 0)

    emotion_list = list()

    for i in range(len(class_names)):
        emotion_list.append((class_names[i], score.data.cpu().numpy()[i]))
    emotion_list.sort(key=lambda x:x[1], reverse=True)
    print(emotion_list)
    print("cv")

@socketio.on('my event')
def handle_my_custum_event(json, methods=['GET', 'POST']):
    print('received json' + str(json))

@socketio.on('uploadFile')
def uploadFile(data):
    username, video_file = data['data']['uid'], data['data']['file']
    video_count = len(os.listdir('./videos/{0}'.format(username)))
    with open('./videos/{0}/{1}{2}.avi'.format(username, username, video_count), 'wb') as f:
        f.write(video_file);

def rgb2gray(rgb):
    return np.dot(rgb[...,:3], [0.299, 0.587, 0.114])


cut_size = 44

transform_test = transforms.Compose([
transforms.TenCrop(cut_size),
transforms.Lambda(lambda crops: torch.stack([transforms.ToTensor()(crop) for crop in crops])),
])

device = torch.device('cpu')
net = VGG('VGG19')
checkpoint = torch.load(os.path.join('FER2013_VGG19', 'PrivateTest_model.t7'), map_location=device)
net.load_state_dict(checkpoint['net'])
net.eval()


if __name__ == '__main__':
    app.debug = True
    print("server start!")
    socketio.run(app, host='127.0.0.1', port=8000)