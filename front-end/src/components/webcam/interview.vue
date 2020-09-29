<template>
    <div class = "back">
        <v-app-bar
        color="white"
        fixed

        >   
        <v-slide-x-transition>
            <v-img
                src="@/assets/images/Temp_Logo2.jpg"
                class="shrink"
                contain
                height="50"
                style="left"
            />
        </v-slide-x-transition>

        <v-spacer />

        <v-btn
            dense
            dark
            color="secondary"
            small
            style="margin-left:5px;"
            @click="logout"
        >
            로그아웃
        </v-btn>
    </v-app-bar>

        <v-main>
            <section id = "interview">

                <div class ="text-center" style ="margin-top:200px">
                    <video id="inputVideo" :width=width :height=height poster="../../assets/images/webcam_2.jpg"></video>
                </div>

                <div class ="text-center" style ="margin-top:40px">
                    <v-btn fab dark large color ="red darken-4" id ="actionBtn">Record</v-btn>
                </div>
            </section>
        </v-main>

        <v-speed-dial fixed bottom right open-on-hover v-model="fab" style="margin-bottom: 100px;">
            <v-btn
                slot="activator"
                fab
                v-model="fab"
            >
                <v-icon v-if="fab">mdi-close</v-icon>
                <v-icon v-else>mdi-plus</v-icon>
            </v-btn>
            <router-link to="/board">
                <v-btn fab small>
                <v-icon>fas fa-book</v-icon>
                </v-btn>
            </router-link>
            <v-btn fab small>
                <v-icon>mdi-account-circle</v-icon>
            </v-btn>
            <v-btn fab small>
                <v-icon @click="goHome">mdi-home</v-icon>
            </v-btn>
        </v-speed-dial>


        <v-bottom-navigation
                dark
                fixed
                color="#292929"
                height="100"
        >
        <div class="title font-weight-light grey--text text--lighten-1 text-center" style="line-height: 100px;">
            &copy; {{ (new Date()).getFullYear() }} — AI Interview — Made with 💜 by Team.Aight
        </div>
        </v-bottom-navigation>
        
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
            dst : null,
            videodata : null,
            cap : null,
            actionBtn: null,
            streaming : false,
            stream : null,
            width : 0,
            height : 0,
            FPS : 30,
            fab: false,
        }
    },
    created(){
        // console.log("Created Start")
        // this.socket = io('http://j3a308.p.ssafy.io:8000', {transports : ['websocket']})
        this.socket = io('ws://127.0.0.1:8000', {transports : ['websocket']})
        // console.log(this.socket)
        this.socket.on('MESSAGE', (socket) =>{
            // console.log(socket);
            this.message = socket;
            console.log(this.message);
        })
        // console.log("End")
        this.width = 320
        this.height = 240
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
                this.dst = new cv.Mat(this.height, this.width, cv.CV_8UC1);
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
        async processVideo(){
            let uploadToServer = function(socket, data) {
                socket.emit('cvdata', {'data' : data});
            };

            if (!this.streaming) {
            this.src.delete();
            this.dst.delete();
            return;
            }

            const begin = Date.now();
            this.cap.read(this.src);
            // console.log(this.src);
            cv.cvtColor(this.src, this.dst, cv.COLOR_RGBA2GRAY);
            
            await uploadToServer(this.socket, this.src.data);

            this.socket.on('res', function(data) {
                console.log(data);
            });

            // console.log(this.cap);
            const delay = 1000/this.FPS - (Date.now() - begin);
            setTimeout(this.processVideo, delay+1000);
        },
        logout(){
            this.$session.destroy();
            this.$router.push("/");
        },
        goHome(){
        this.$router.push('/');
        },
    }
}
</script>

<style scoped>
  a{ color: black; }
  a:link { text-decoration: none; }
  a:visited { text-decoration: none; }
  a:hover { color: blue; }
  a:active { text-decoration: none; }

  .back{
    background-image: url("../../assets/images/background.png");
    background-size: cover;
    height: 100%;
  }
</style>