<template>
    <div class ="videoapp">
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
            <v-row>
                <v-col class ="d-flex align-center">
                    <div id="videoPlay" class = "text-center">
                    <vue-plyr>
                        <video
                            crossorigin
                            playsinline
                            poster="https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-HD.jpg"
                            v-bind:src="videosrc"
                            
                        >
                            
                        </video>
                    </vue-plyr>
                    </div>
                </v-col>
                
                <v-col>
                    <v-row class ="d-flex align-center">
                        <v-col>
                        <br>
                        <v-file-input 
                        id="file_input"
                        @change="handleUpload($event)" 
                        accept="video/*"
                        dense
                        outlined
                        prepend-icon="mdi-file-video"
                        placeholder="영상을 올려주세요."
                        />
                      
                        <v-btn id ="upload_btn" @click="uploadFile">Upload</v-btn>
                        </v-col>
                        
                    </v-row>
                </v-col>
            </v-row>
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

import VuePlyr from 'vue-plyr'
import io from 'socket.io-client'


export default {
    name : 'uploadVideo',
    components:{
        VuePlyr
    },
    data(){
        return{
            socket: null,
            upload: null,
            fab: false,
            upload_file:[],
            videosrc: null,
            videotype:null,
            uid: "",
        }
    },
    created() {
      // this.socket = io('http://j3a308.p.ssafy.io:8000', {transports : ['websocket']})
        this.socket = io('ws://127.0.0.1:2346', {transports : ['websocket']})
        // this.uid = this.$session.get("user").uid;
        this.uid = "kang";
    },
    mounted(){
        
    },
    methods:{
        logout(){
            this.$session.destroy();
            this.$router.push("/");
        },
        goHome(){
        this.$router.push('/');
        },
        handleUpload(event){
            // this.upload_file = event.target.files[0];
            // this.videosrc = this.upload_file.name;
            // this.videotype = this.upload_file.type;
            // console.log(event)
            // var filePath = event.value;
            // console.log(event)
            this.upload_file = event;
            // console.log(this.upload_file)
            this.videosrc= event.name;
            
            console.log(document.getElementById("file_input").files[0])
        },
        uploadFile(){
            this.upload = {'uid' :this.uid,'file' : this.upload_file, 'type' : this.upload_file.type }
            this.socket.emit('uploadFile', {'data' : this.upload})
        },
        checkFile(){
            this.socket.on('getVideo', (video)=>{
                this.videosrc = video.src;
            })
        }
    }
}
</script>

<style>
    .videoapp {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
        background-image: url("../../assets/images/background.png");
    }
    #videoPlay {
        width: 100%;
        height: 100%;
        
    }
    a{ color: black; }
    a:link { text-decoration: none; }
    a:visited { text-decoration: none; }
    a:hover { color: blue; }
    a:active { text-decoration: none; }

    .back{
        background-image: url("../../assets/images/bbback.jpg");
        background-size: cover;
        height: 100%;
    }
    #file_input{

        width: 50%;
    }
</style>