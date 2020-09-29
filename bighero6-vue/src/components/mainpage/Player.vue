<template>

<div > 
    <youtube hidden :video-id="videoId" @ended="end" ref="youtube" ></youtube>
<v-card style="text-align: center;">
      <h3 id="playing" style ="font-family: 'Do Hyeon', sans-serif;" class="pt-2"><v-icon small left>mdi-music</v-icon>{{diaryDetail.song.singer}}<v-icon small left>mdi-music</v-icon></h3>
      <h3 id="playing" style ="font-family: 'Do Hyeon', sans-serif; ">{{diaryDetail.song.title}}</h3>
      
            <v-btn outlined icon class="mx-1"  @click.native="playing ? pause() : play()" >
                <v-icon   v-if="!playing || paused" >mdi-play</v-icon>
                <v-icon  v-else >mdi-pause</v-icon>
            </v-btn>
            <v-btn outlined icon class="mx-1"  @click.native="stop()">
                <v-icon>mdi-stop</v-icon>
            </v-btn>
            <div style="width:95%;">
                <v-icon class ="ml-1" style ="float:left;" flat >mdi-volume-high</v-icon>     
                 <v-slider style ="height:35px" v-model="volume" @input="updateVolume(volume)" max="100" step="1"></v-slider>
            </div>     
  
         <!--    <v-btn outlined icon class="ma-2" :color="color" @click.native="loaded ? download() : reload()" v-if="!loaded">
                <v-icon>mdi-refresh</v-icon>
            </v-btn>
            <v-btn outlined icon class="ma-2" :color="color" @click.native="loaded ? download() : reload()" v-if="loaded && downloadable">
                <v-icon>mdi-download</v-icon>
            </v-btn> -->
            <!--  <v-progress-linear v-model="percentage" height="5" style="margin-top: 15px; margin-bottom: 15px;" @timeupdate="_handlePlayingUI" @click.native="setPosition()" ></v-progress-linear>  -->
           <!--  <p>{{ currentTime }} / {{ Duration }}</p> -->
      
    </v-card>
    
</div>
    
</template>

<script>
import Vue from 'vue'
import VueYoutube from 'vue-youtube'
const axios = require("axios"); 
const cheerio = require("cheerio");
const request = require("request");

 
Vue.use(VueYoutube)

export default {
components: {
    VuetifyAudio: () => import('vuetify-audio'),
  },
  data () {
            return {
                videoId: '7-x3uD5z1bQ',
                firstPlay: true,
                isMuted: false,
                loaded: false,
                playing: false,
                paused: false,
                percentage: 0,
                currentTime: '00:00:00',
                audio: undefined,
                totalDuration: 0,
                volume:100
            }
        },

     props: {
          diaryDetail : Object ,
        //     flat: {
        //         type: Boolean,
        //         default: false
        //     },
        //     file: {
        //         type: String,
        //         default: null
        //     },
        //     autoPlay: {
        //         type: Boolean,
        //         default: false
        //     },
        //     ended: {
        //         type: Function,
        //         default: () => {},
        //     },
        //     canPlay: {
        //         type: Function,
        //         default: () => {},
        //     },
        //     color: {
        //         type: String,
        //         default: null
        //     },
        //     downloadable: {
        //         type: Boolean,
        //         default: false
        //     }
        },
        computed: {
             player() {
              return this.$refs.youtube.player;
            },
            Duration() {
                return this.totalDuration;
            },  
        },
   mounted () {
      //console.log("https://www.youtube.com/results?search_query="+this.diaryDetail.song.singer+"+"+this.diaryDetail.song.title);
    //   this.getHTML().
    //   then(html => { let ulList = []; const $ = cheerio.load(html.data); const $bodyList = $("div.ah_list.PM_CL_realtimeKeyword_list_base ul.ah_l").children("li.ah_item"); $bodyList.each(function(i, elem) { ulList[i] = { title: $(this).find('span.ah_k').text(), url: $(this).find('a.ah_a').attr('href') }; }); const data = ulList.filter(n => n.title); return data; }) .then(res => log(res));
   const options = {
    url: 'https://www.naver.com',
    headers: {
        'User-Agent': 'request'
    }
};

const callback = ((error, response, body) => {
    if (!error && response.statusCode == 200) {
        const $ = cheerio.load(body);
        const search = $('.ah_roll_area  > .ah_l > .ah_item > a > .ah_k');
        search.each((index, item) => {
            console.log(`${index + 1} - ${$(item).text()}`);
        });
    }
});
request(options, callback);

  } ,      

  methods: {
    async getHTML() {
        try {
    //return await axios.get("https://www.youtube.com/results?search_query="+this.diaryDetail.song.singer+"+"+this.diaryDetail.song.title);
     return await axios.get('https://www.naver.com/',{headers : {"Access-Control-Allow-Origin" : "*"}});
     //{headers : {'Access-Control-Allow-Origin' : '*'}}
  } catch (error) {
    console.error(error);
  }
    }, 
    updateVolume(volume){
        this.player.setVolume(volume)
    },
    end(){
        this.paused=true;
    },
    play() {
      this.player.playVideo()
       this.playing=true;
       this.paused=false;
    },
      pause() {
      this.player.pauseVideo()
      this.playing=false;
      this.paused=true;
    },
    stop() {
        this.player.stopVideo()
        this.playing=false;
    }
    ,
    mute(){
        this.isMuted=false;
    } ,
    getId (url) {
      return this.$youtube.getIdFromUrl(url)
    },
    setPosition(){
       this.playergetCurrentTime = parseInt(this.player.getDuration / 100 * this.percentage);
    },
    //  init() {
    //             this.player.addEventListener('timeupdate', this._handlePlayingUI);
    //             /* this.player.addEventListener('loadeddata', this._handleLoaded); */
    //            /*  this.player.addEventListener('pause', this._handlePlayPause);
    //             this.player.addEventListener('play', this._handlePlayPause);
    //             this.player.addEventListener('ended', this._handleEnded); */
    //         }, 
    //  _handleLoaded: function () {
    //             if (this.player) {
    //               /*   if (this.player.getDuration === Infinity) {
    //                     // Fix duration for streamed audio source or blob based
    //                     // https://stackoverflow.com/questions/38443084/how-can-i-add-predefined-length-to-audio-recorded-from-mediarecorder-in-chrome/39971175#39971175
    //                     this.player.getCurrentTime = 1e101;
    //                     this.player.ontimeupdate = () => {
    //                         this.player.ontimeupdate = () => {}
    //                         this.player.getCurrentTime = 0
    //                         this.totalDuration = parseInt(this.player.getDuration)
    //                         this.loaded = true;
    //                     }
    //                 } else */ 
    //                 {
    //                     this.player.getDuration().then(function(data){

    //                     this.totalDuration =data;
    //                     })
                       
    //                     this.loaded = true
    //                 }
    //                 if (this.autoPlay) this.player.playVideo()
    //             } else {
    //                 throw new Error('Failed to load sound file')
    //             }
    //         },
    //         _handlePlayingUI: function (e) {
    //           var ct ,gd;
    //             this.player.getCurrentTime().then(function(data){
    //              ct = parseInt(data)+1;  
    //            });
    //               this.player.getDuration().then(function(data){
    //              gd = parseInt(data)+1;  
    //            });
               
    //           this.percentage = (ct/gd) * 100
    //           this.currentTime = ct;
                
                
    //             this.playing = true
    //         },
    //         _handlePlayPause: function (e) {
    //             if (e.type === 'play' && this.firstPlay) {
    //                 // in some situations, audio.currentTime is the end one on chrome
    //                 this.audio.currentTime = 0;
    //                 if (this.firstPlay) {
    //                     this.firstPlay = false;
    //                 }
    //             }
    //             if (e.type === 'pause' && this.paused === false && this.playing === false) {
    //                 this.currentTime = '00:00:00'
    //             }
    //         },
    //         _handleEnded () {
    //             this.paused = this.playing = false;
    //         },
 
  } ,
  

}
</script>

<style>


</style>
