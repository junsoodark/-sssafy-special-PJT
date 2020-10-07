<template>
<div>
    <!--노래 검색 모달 버튼  -->
 <div v-if ="isMy" class="text-left searchSongModal">
        <v-dialog
          v-model="dialog2"
          width="500"
        >
          <template v-slot:activator="{ on, attrs }" style = "float:right;">
            <v-btn
            dark
            tile
      v-bind="attrs"
      v-on="on"
      color="blue"
      class="ma-2"
      style = "float:right;"

    >노래 추가
      <v-icon
      large
        right
        dark
      >
        mdi-music-circle-outline
      </v-icon>
    </v-btn>
 
          </template>

          <v-card>
            <v-card-title class="headline grey lighten-2">
              노래 검색
              <v-text-field
                class="songSearch mx-auto mb-2"
                flat
                hide-details
                label=" 노래 제목으로 검색하세요"
                prepend-inner-icon="mdi-magnify"
                solo-inverted
                v-model="songKeyword"
                @keyup="searchSong()"
              ></v-text-field>
            </v-card-title>

            <v-card-text style="padding-top:20px;" v-if="songKeyword != ''">
              <v-card light
                max-width="600"
                class="mx-auto pa-3"
              >
                <v-virtual-scroll
                  :items="songList"
                  height="400"
                  item-height="64"
                >
                  <template v-slot="{ item }">
                    <v-list-item >
                      <v-list-item-avatar>
                          <img :src="albumUrl(item.album_id)">
                        </v-list-item-avatar>

                      <v-list-item-content>
                        <v-list-item-title>
                          <div style="float:left; width:26px;" class="mt-2">
                           {{item.artist}} <br>
                          <strong style="over-flow:hidden">{{ item.song_name }}</strong></div>
                           <!-- <v-btn style= "float:right">추가</v-btn> -->
                           <v-btn @click="addSong(item)" 
                            style= "float:right" class="ma-2"
       color="indigo"
        icon
        dark
        
      >
         <v-icon large >mdi-music-note-plus</v-icon>
      </v-btn>
                          
                        </v-list-item-title>
                       
                      </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                  </template>
                </v-virtual-scroll>
              </v-card>
            </v-card-text>

            <v-divider></v-divider>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="primary"
                text
                @click="dialog2 = false"
              >
                닫기
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>

<!--  ---------- -->
  <youtube hidden  @ended="end" ref="youtube" ></youtube>
  
    <v-card height="330" tile dark :class="{playlist}">
      <div v-show="isEmpty" style="text-align:center;" class="container">
        <h3>해당 플레이리스트에 </h3>
    <h3>재생목록이 없습니다 <v-icon>mdi-playlist-music-outline</v-icon></h3>
      </div>
    <v-list v-show="!isEmpty">
      <v-list-item @click="selectTrack(index,track)"
        v-for="(track, index) in playlist"
        :key="track"
        :class="[{selected: index === selectedNumber}, {even: index % 2 == 0}]"
        >
        <v-list-item-content >
          <!-- <v-img :src="albumUrl(track.id)"></v-img> -->
          
          <v-list-item-title><v-avatar class="mr-3"><v-img :src="albumUrl(track.album_id)"></v-img></v-avatar>{{ track.artist }}  -  {{ track.song_name }} </v-list-item-title>
            </v-list-item-content>
        <v-spacer></v-spacer>
        <!-- {{ track.howl.duration() }} -->
      </v-list-item>
    </v-list>
   
  </v-card>
  <v-card tile height="60" dark v-show="selectedTrack&&!isEmpty">
    <v-card-title>
      <h2>{{ selectedTrack.artist }} - {{ selectedTrack.song_name }}</h2>
      <v-spacer></v-spacer>
      <!-- <h3>{{trackInfo.seek | minutes}}/{{trackInfo.duration | minutes}}</h3> -->
    </v-card-title>    
  </v-card>
      <v-toolbar flat height=90 dark v-show="!isEmpty">
      <v-spacer></v-spacer>
      <v-btn outlined fab small color="light-blue" @click="prev()">
  <v-icon >skip_previous</v-icon>
</v-btn>
      <v-btn outlined fab small color="light-blue" @click="stop()">
        <v-icon>stop</v-icon>
      </v-btn>
      <v-btn outlined fab color="light-blue" @click="play()">
        <v-icon large>play_arrow</v-icon>
      </v-btn>
      <v-btn outlined fab small color="light-blue" @click="pause()">
        <v-icon>pause</v-icon>
      </v-btn>
      <v-btn outlined fab small color="light-blue" @click="next()">
  <v-icon>skip_next</v-icon>
</v-btn>
      <v-spacer></v-spacer>
    </v-toolbar>
    </div>
</template>

 
<script>
import Vue from 'vue'
import VueYoutube from 'vue-youtube'
import axios from 'axios'
import constants from "../../lib/constants"
 
Vue.use(VueYoutube)
  export default {
    props: {
      playlist: Array,
      isMy : Boolean,
      playlistId : String,

    },
    watch: {
    playlist: function () {
      if(this.playlist.length==0){
             this.isEmpty = true;
      }else{
        this.isEmpty = false;
        //this.selectedNumber = 0;
        //this.selectedTrack = this.playlist[0];
        this.playlists = this.playlist.map(a=>a.youtubeId)
        //console.log(this.playlists)
       //let result = objArray.map(a => a.foo);
        this.player.cuePlaylist(this.playlists,'playlist',0,0,'default')
         this.player.pauseVideo();

      }
     
      },
       dialog2 : function(){
        if(!this.dialog2) {
        this.songKeyword = ''
        }
    }
    },
    mounted(){
      this.selectedNumber = 0;
      this.selectedTrack = this.playlist[0];
      this.playlists = this.playlist.map(a=>a.youtubeId)
       //console.log(this.playlists)
       //let result = objArray.map(a => a.foo);
       this.player.cuePlaylist(this.playlists,'playlist',0,0,'default')
       //this.player.stopVideo();
    },
     data () {
            return {
                playlists: [],
                videoId: '',
                firstPlay: true,
                isMuted: false,
                loaded: false,
                playing: false,
                paused: false,
                percentage: 0,
                currentTime: '00:00:00',
                audio: undefined,
                totalDuration: 0,
                volume:100,
                selectedTrack :'',
                selectedNumber :0,
                isEmpty : false,
                dialog2 : false,
                songKeyword : '',
                songList:[],
            }
        },
        computed: {
             player() {
              return this.$refs.youtube.player;
            },
            Duration() {
                return this.totalDuration;
            },  
        },
    methods: {
      addSong(song){
        console.log(this.playlistId)
         axios.post(constants.baseUrl + "/playlist/song", {
           playlistId : this.playlistId,
           songId : song.id
         },{ headers : { "Authorization": "Bearer "+ this.$store.state.authToken} }) // 토큰 인증을 위해 헤더에 내용 추가
         .then(( {data} ) => {
             if(data.status ==200){
               alert("노래 추가 완료!")
               this.playlist.push(song);
               this.playlists.push(song.youtubeId);
               this.dialog2 =false;
             }
         })
        .catch(function (error) {
           console.log(error);
         });

      },
       searchSong() {
      axios
      .get(constants.baseUrl + "/music", { 
        headers : {"Authorization": "Bearer "+ this.$store.state.authToken} ,
        params : { keyword : this.songKeyword }
      }) // 토큰 인증을 위해 헤더에 내용 추가
      .then(({ data }) => {
        console.log(data);
        this.songList = data;
      })
      .catch(function (error) {
        console.log(error);
      });
    },
       pad(n, width) {

       n = n + '';

     return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;},
    albumUrl(url){
       //10197480
       //console.log(url)
       url = url+'';
       var number  = this.pad(url,8);
       var imgurl = 'https://cdnimg.melon.co.kr/cm/album/images/'+ number.slice(0,3)+'/'+ number.slice(3,5)+'/'+number.slice(5,8)+'/'+url+'_500.jpg';
       return imgurl;
   },
  selectTrack (index, track) {
     this.selectedTrack = track;
     this.selectedNumber= index;
     //this.player.playVideoAt(index);
  },
  updateVolume(volume){
        this.player.setVolume(volume)
    },
    end(){
        this.paused=true;
    },
    play() {
      //this.player.playVideo()
       this.player.playVideoAt(this.selectedNumber)
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
    ,prev(){
        this.player.previousVideo();
        this.selectedNumber = (this.selectedNumber-1)%this.playlist.length;
        this.selectedTrack = this.playlist[this.selectedNumber];

    },
    next(){
        this.player.nextVideo();
         this.selectedNumber = (this.selectedNumber+1)%this.playlist.length;
        this.selectedTrack = this.playlist[this.selectedNumber];
    },
    mute(){
        this.isMuted=false;
    } ,
    getId (url) {
      return this.$youtube.getIdFromUrl(url)
    },
    setPosition(){
       this.playergetCurrentTime = parseInt(this.player.getDuration / 100 * this.percentage);
    },
}
  }
</script>

<style scoped>
  .selected {
    background-color:  orange !important;
  }
  .even {
    background-color: #505050
  }
  .playlist {
    overflow: auto
  }


  .searchSongModal{
    position: absolute;
    left: -20px;
    top : -50px;
    z-index:10;
  }
</style>