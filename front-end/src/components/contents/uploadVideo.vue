<template>
    <div id ="videoapp">
        <v-app-bar
        color="white"
        fixed
        >   
            <v-layout row>
                <v-flex xs6 md1 lg1>
                <v-img
                    src="@/assets/images/ProjectLogo.png"
                    class="shrink"
                    contain
                    height="50"
                />
                </v-flex>
                <v-flex  md7 lg9>
                </v-flex>
                <v-flex xs3 md2 lg1>
                    <v-btn
                    dense
                    dark
                    color="secondary"
                    small
                    style="margin-left:5%; margin-top:6%;"
                    @click="logout"
                    >
                    마이페이지
                    </v-btn>
                </v-flex>
                <v-flex xs3 md2 lg1>
                    <v-btn
                        dense
                        dark
                        color="secondary"
                        small
                        style="margin-left:5%; margin-top:6%;"
                        @click="logout"
                    >
                        로그아웃
                    </v-btn>
                </v-flex>
            
            </v-layout>
            
        </v-app-bar>

        <v-container 
            fluid 
            id="mainContainer"
            fill-height
            
        >
            <v-layout row no-gutters>
                <v-flex md12 lg7>
                    <div id="videoPlay" class = "text-center">
                    <vue-plyr>
                        <video
                            crossorigin
                            playsinline
                            poster="https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-HD.jpg"
                            :src="videosrc"
                        >
                            
                        </video>
                    </vue-plyr>
                    </div>
                </v-flex>
                
                <v-flex sm8 md6 lg4>
                    <div id="buttonArea">
                    <v-flex sm12 md6 lg10>
                        <v-col align-self="center">
                            <br>
                            <v-file-input 
                            id="file_input"
                            @change="handleUpload($event)" 
                            accept="video/*"
                            dense
                            outlined
                            show-size
                            color="primary"
                            background-color="white"
                            label="영상을 선택해 주세요."
                            >
                                <template v-slot:selection="{ text }">
                                    <v-chip
                                        small
                                        label
                                        color="black"
                                        text-color="white"
                                    >
                                        {{text}}
                                    </v-chip>
                                </template>
                            </v-file-input>
                        </v-col>
                    </v-flex>
                    <v-flex sm12 md6 lg12>
                        <v-flex align-self="center">
                            <v-btn id ="upload_btn" @click="uploadFunc">
                                Upload
                                <v-icon right dark color="primary">mdi-cloud-upload</v-icon>
                            </v-btn>
                            <v-btn id ="result_btn" @click="uploadFunc">
                                Go To Result
                                <v-icon right dark color="primary">mdi-cloud-upload</v-icon>
                            </v-btn>
                        </v-flex>
                    </v-flex>
                    </div>
                </v-flex>
            </v-layout>
        </v-container>

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
                color="#292929"
                height="100"
                fixed
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
import { mapGetters } from 'vuex'
import axios from 'axios'

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
            currentFile: undefined,
            progress: 0,
            message: "",
            fileInfos: [],
        }
    },
    computed : {
        ...mapGetters([
            'username',
        ])
    },
    created() {
    //   this.socket = io('http://j3a308.p.ssafy.io:8000', {transports : ['websocket']})

        this.socket = io('ws://127.0.0.1:8080', {transports : ['websocket']})

        // this.uid = this.$session.get("user").uid;
        this.uid = "kang";
        this.fab = false;
       
    },
    mounted(){
        
    },

    updated() {
        // this.videosrc = '../../../../face_api/videos/kang/kang1.mp4';
    },
    methods:{
        selectFile(file){
            this.progress = 0;
            this.currentFile = file;
        },
        logout(){
            this.$store.dispatch('logout');
            this.$router.push("/");
        },
        goHome(){
        this.$router.push('/main');
        },
        handleUpload(event){
            // this.upload_file = event.target.files[0];
            // this.videosrc = this.upload_file.name;
            // this.videotype = this.upload_file.type;
            // console.log(event)
            // var filePath = event.value;
            // console.log(event)
            if(this.upload_file.length == 0){
                this.upload_file = event;
            }else{
                this.upload_file = [];
                console.log(this.upload_file)
            }
            
            // console.log(this.upload_file)
            // this.videosrc= event.name;
            
            console.log(document.getElementById("file_input").files[0])
        },
        uploadFunc(){
            if(this.upload_file.length == 0){
                alert("영상 파일을 올려 주세요.")
                return;
            }else{
                this.uploadFile()
            }
        },
        async uploadFile(){
            console.log("uploadFile : " + this.upload_file)
            
            let uploadToServer = function(socket, data) {
            socket.emit('uploadFile', {'data' : data})
            };
            this.upload = {'uid' :this.username,'file' : this.upload_file, 'type' : this.upload_file.type };
            let fileName = this.username;
            let fileType = this.upload_file.type.split('/')[1];
            await uploadToServer(this.socket, this.upload);
            this.socket.on('success', function(data) {
                this.videoSrc = `../../../../face_api/videos/${fileName}/${fileName}1.${fileType}`;
                console.log(data);
                    // let path = JSON.parse(data['data'])['path'];
                    // this.videosrc = `${path}\\` + `${fileName}1.${fileType}`;
                    // console.log(this.videosrc);
            });
            this.socket.on('res', function(data) {
                let result = JSON.parse(data['data'])['point_list'];
                console.log(result);
                axios({
                method: "post",
                url: "http://localhost:8081/api/interviewresult",
                data: {
                    username : this.username,
                    image_score : parseFloat(result[result.length-1]),
                    image_score_list : result,
                    voice_score : 0,
                    silent_interval : '',
                    graph_image_url : '',
                    feedback : 'None',
                    video_length : 0,
                    is_live : false,
                    filename : fileName,
                    test_date : Date.now(),
                },
                }).then(res => {
                    console.log(res);
                    alert('영상 업로드가 완료되었습니다!');
                }).catch(err => console.log(err));
            });
        }    

        }
    
}
</script>

<style>
    #mainContainer {
        /* font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50; */
        margin-top: 10%;
        margin-left: 3%;
        width: 100%;
        height: 75%;
        
    }
    #videoapp{
        background-image: url("../../assets/images/background.png");
        background-size: cover;
        height: 100%;
    }
    #videoPlay {
        margin-left: 2%;
        width: 90%;
        height: 100%; 
    }
    #buttonArea {
        margin-top: 10%;
    }
    /* a{ color: black; }
    a:link { text-decoration: none; }
    a:visited { text-decoration: none; }
    a:hover { color: blue; }
    a:active { text-decoration: none; } */

    /* .back{
        background-image: url("../../assets/images/bbback.jpg");
        background-size: cover;
        height: 100%;
    } */
    #upload_btn{
        margin-left: 5%;
    }
    #result_btn{
        margin-left: 20%;
    }
    #file_input{

        margin-left: 30%;
    }
@media screen and (min-width: 960px) {

    #buttonArea {
        margin-top: 10%;
    }
}

@media screen and (max-width: 960px) {

    #buttonArea {
        margin-top: 5%;
    }

    #upload_btn{
        margin-left: 10%;
    }
    #result_btn{
        margin-left: 30%;
    }
}


    
</style>