<template>
    <div>
        <v-img src="../../assets/images/background.png">
            <v-content>
                <section id = "interview">
                    <div class ="text-center">
                        <p>InterView Page</p>
                        <video id="inputVideo" :width=width :height=height poster="../../assets/images/webcam_test.jpg"></video>
                    </div>

                    <div class ="text-center">
                        <v-btn class="my-2" fab dark large color ="red darken-4" id ="actionBtn">Record</v-btn>
                    </div>
                </section>
            </v-content>
            <v-footer
            class="justify-center"
            color="#292929"
            height="100"
            >
            <div class="title font-weight-light grey--text text--lighten-1 text-center">
                &copy; {{ (new Date()).getFullYear() }} — AI Interview — Made with 💜 by Team.Aight
            </div>
            </v-footer>
        </v-img>
    </div>

    
</template>


<script>

// const io = require('socket.io-client')
import io from 'socket.io-client'
import cv from 'opencv.js'

export default {
    name: 'socketTest',
    data(){
        return{
            message: [],
            socket: null,
            src : null,
            // dst : null,
            videodata : null,
            cap : null,
            actionBtn: null,
            streaming : false,
            stream : null,
            width : 0,
            height : 0,
            FPS : 30
        }
    },
    created(){
        // console.log("Created Start")
        // this.socket = io('http://j3a308.p.ssafy.io:8000', {transports : ['websocket']})
        this.socket = io('ws://127.0.0.1:2346', {transports : ['websocket']})
        // console.log(this.socket)
        this.socket.on('MESSAGE', (socket) =>{
            // console.log(socket);
            this.message = socket;
            console.log(this.message);
        })
        // console.log("End")
        this.width = 500
        this.height = 400
        // console.log(this.message)
    },
    mounted(){
        this.videodata = document.getElementById('inputVideo');
        this.actionBtn = document.getElementById('actionBtn');
        
        this.onCvLoaded();
        this.cap = new cv.VideoCapture(this.videodata);
    },
    methods :{
        onCvLoaded(){
            cv.onRuntimeInitialized = this.onReady();
        },
        onReady(){

            this.actionBtn.addEventListener('click', ()=>{
                if(this.streaming){
                    this.stop();
                    this.actionBtn.textContent ='Record';
                }else{
                    this.Record();
                    this.actionBtn.textContent ='Stop'
                }
            });
        },
        Record(){
            navigator.mediaDevices.getUserMedia({ video: true, audio: false })
            .then(_stream => {
                this.stream = _stream;
                // console.log('stream', this.stream);
                this.videodata.srcObject = this.stream;
                this.videodata.play();
                this.streaming = true;
                this.src = new cv.Mat(this.height, this.width, cv.CV_8UC4);
                // this.dst = new cv.Mat(this.height, this.width, cv.CV_8UC1);
                setTimeout(this.processVideo, 0)
            })
            .catch(err => console.log(`An error occurred: ${err}`));
        },
        stop(){
            if (this.videodata) {
                this.videodata.pause();
                this.videodata.srcObject = null;
            }
            if (this.stream) {
                this.stream.getVideoTracks()[0].stop();
            }
            this.streaming = false;
        },
        processVideo(){
            if (!this.streaming) {
            this.src.delete();
            // this.dst.delete();
            return;
            }
            const begin = Date.now();
            this.cap.read(this.src);
            // console.log(this.src);
            // cv.cvtColor(this.src, this.dst, cv.COLOR_RGBA2GRAY);
            
            // console.log(this.dst);
            this.socket.emit('cvdata', {'data': this.src.data});
            // console.log(this.cap);
            const delay = 1000/this.FPS - (Date.now() - begin);
            setTimeout(this.processVideo, delay);
        }
    }
}
</script>

<style>

</style>