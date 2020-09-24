<template>
<div id ="diary">
<div id = "book">

  <div class="book">
    <div class="first paper" @click = "openBook()">
      <div class="page front contents">
        <div class="intro">
          <h2>DIARY</h2>
          <h1>2020</h1>
        </div>
      </div>
      <div class="page back"></div>
    </div>
    <div class="second paper">
      <div class="page last front contents">
        <calendar @showDetail="getDiaryDetail" v-if ="open" class="calendar"></calendar>
      </div>
      <div class="page back"></div>
    </div>
   <!--  <div class="third paper">
      <div class="page front contents">
        <div id="vara-container2"></div>
      </div>
      <div class="page back"></div>
    </div> -->
   <!--  <div class="fourth paper">
      <div class="page last front contents">
        <div id="vara-container3"></div>
      </div> 
      <div class="page back"></div>
    </div> -->
    <div class="side"></div>
    <div class="bottom"></div>
    <div class="shadow"></div>
  </div>
   <span v-if ="open" class="penIcon" @click="moveWriteDiary" >일기 쓰기 <v-icon    large color="black" >mdi-lead-pencil</v-icon></span>
</div>

  <div v-if="diaryDetail" id="player" >
  
      <div class="cd-player">
  <div class="cd-player-inner">
    <div class="play-cover">

    </div>
    <div class="cd">
       <div class="album-cover spin">
        <img :src="albumUrl(diaryDetail.music.musicID)" alt="">

       </div>
        <div class="song-info">
        <h2 class="song-title">{{diaryDetail.music.title}}</h2>
        <h3 class="song-singer">{{diaryDetail.music.singer}}</h3>
        <h4 class="song-hits"></h4>
       </div>
      
    </div>
  </div>
</div>
 
 </div> 
 <div v-if="diaryDetail"  class ="musicPlayer" style="text-align: center;">
  <player></player>
  </div> 



     <div id ="diaryDetail" class="note" v-if="diaryDetail">
      <div class="header" >
          <h1>{{diaryDetail.date}}</h1>
          <h2>날씨 <img :src = "weather(diaryDetail.weather)" style ="width:30px;height:30px;"></h2>
           <h2>기분 <img :src = "emotion(diaryDetail.emotion)" style ="width:30px;height:30px;"></h2>
       
        <div class="ma-3">
          <v-img :src="diaryDetail.contentImg"  style ="width:100%;height:150px;"></v-img>
       
        <div class="mt-4 v-sheet theme--light elevation-3 diaryText" >
               {{diaryDetail.content}}
        </div>
         </div>
      </div>
    </div>


 </div>


  
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
span{
  font-family: 'Do Hyeon', sans-serif;
}
/*

Click the diary to start animating.
View the project on Github : https://github.com/akzhy/Vara

*/
#diary{
  background: #fff4d3;height: 1000px;
}



.book {
  transform-style: preserve-3d;
 /*  transform: rotateX(45deg) rotateY(0deg) rotateZ(-45deg); */
  transition: transform 1s;
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  /* bottom: 5%;  */
  margin-top: 15px;  
  margin-left: 30%;
  width: 20em;
  height: 600px;
  
}
.book .side {
  width: 3em;
  height: 650px;
  background: #b36060;
  position: absolute;
  left: -3em;
  top: 0;
  transform-origin: 100% 100%;
  transform: rotateY(-90deg) rotateX(0deg);
}
.book .bottom {
  width: 30em;
  height: 3em;
  background: #e2e2e2;
  position: absolute;
  bottom: -em;
  left: 0em;
  transform-origin: 100% 100%;
  transform: rotateX(90deg);
}
.book.open {
  transform: rotateX(0deg) rotateY(0deg) rotateZ(0deg);
 /*  vertical-align: middle; */
}
.book .paper,
.book .shadow {
  width: 430px;
  height: 640px;
  position: absolute;
  top: 0;
  left: 0;
}
.book .shadow {
  background: transparent;
  transform: translateZ(-3em);
  box-shadow: -1em 1em 0px 0px #ccc3a9;
  z-index: 1;
}
.book .paper {
  transition: transform 1s, box-shadow 0.5s 0.2s;
  transform-origin: 0 50%;
  transform-style: preserve-3d;
}
.book .paper.open {
  box-shadow: 2.2em 1em 0px 0px #ccc3a9;
  transform: rotateX(0deg) rotateY(-180deg) rotateZ(0deg);
}
.book .paper .page.front {
  transition: transform 1s;
  transform-origin: 0 50%;
  backface-visibility: hidden;
  z-index: 2;
}

.book .page {
  width: 100%;
  height: 100%;
  position: absolute;
  -webkit-transform: translateZ(0px);
  transform: translateZ(0px);
}
.book .first .page {
  background: #ef9a9a;
}
.book .first .page.front:after {
  position: absolute;
  content: "";
  width: 50%;
  height: 100%;
  left: 0;
  top: 0;
  background: rgba(160, 115, 115, 0.1);
}
.book .paper .back {
  transition: transform 1s;
  transform-origin: 0 50%;
  background: #e1e1e1;
}
/* .book .first.paper .back {
  background: #ef9a9a;
} */
.book .intro {
  position: absolute;
  width: 90%;
  width: calc(100% - 3em);
  height: 90%;
  height: calc(100% - 3em);
  border: 2em solid #eee;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  margin: auto;
}
.book .intro h2 {
  padding: 0.5em 0.5em;
  font-size: 2em;
  color: #fff;
 /*  word-break: break-all; */
  text-align: left;
  letter-spacing: 5px;
}
.book .intro h1 {
  padding: 0.5em 0.25em;
  font-size: 6em;
  color: #fff;
  /* word-break: break-all; */
}
.book .paper:not(.first) .page {
  background: #f1f1f1;
  background-image: linear-gradient(
      90deg,
      transparent 40px,
      #abced4 40px,
      #abced4 42px,
      transparent 42px
    ),
    linear-gradient(#e1e1e1 0.1em, transparent 0.1em);
  background-size: 100% 1.5em;
}
.book .paper:not(.first) .back {
  background: #e1e1e1;
}
.book .open.second .back {
  transform: translateZ(-1px);
}
.book .open.third .back {
  transform: translateZ(-2px);
}
.book .paper.first {
  z-index: 8;
}
.book .paper.second {
  z-index: 7;
}
.book .paper.third {
  z-index: 6;
}
.book .paper.fourth {
  z-index: 5;
}
.book .paper.first .front {
  transform: translateZ(0.4px);
}
.book .paper.second .front {
  transform: translateZ(0.3px);
}
.book .paper.third .front {
  transform: translateZ(0.2px);
}
.book .paper.fourth .front {
  transform: translateZ(0.1px);
}
 .book .second-page #vara-container {
  width: 100%;
  height: 100%;
  padding: 1em;
} 
 #link {
  position: absolute;
  bottom: 10px;
  right: 10px;
  color: #615858;
  letter-spacing: 2px;
  text-decoration: none;
} 

.calendar{
  position: relative;
  top :0px;
  left:-380px; 
  width:180%;
  height: 100%;

  transition: transform 1s;
}
/* ----------------------CD player --------------------- */
#player{
  position: absolute;
  right: 4%;
  top : 25px;
}
/* -----------50----------- NOTE  --------------------- */
#diaryDetail{
  position:absolute;
  right:6%;
  top : 145px;
  height: 500px;
  z-index:0;
  

 
}
.note {
  font-size: 16px;
  border-radius: 2px;
  border: 1px solid #C4C296;
  width: 500px;
  margin: 20px auto;
  background:white;
  overflow:hidden;
  transform-origin: 0% 0%;
  position:absolute;
  padding: 0px 15px;

}
.diaryText{
  text-align: center;
  padding:10% 10%;
  border: 1px solid #faf375ea;
  font-size:20px;
  background: #fcd875;
  width:100%; 
  height:180px;
  font-family: 'Do Hyeon', sans-serif;
}

.penIcon{
  position:absolute;
  left:45%;
  top:40px;
  cursor: pointer;
}
.musicPlayer{
  position: absolute;
  top:10px;
  right:350px;
  width:200px;
  height:90%;
}
</style>
<style lang="scss">
@import "@/style/cdplayer.scss";
</style>
<script>


export default {
components: {
   
    calendar: () => import('@/components/mainpage/Calendar'),
    player: () => import('@/components/mainpage/Player'),
},
  data() {
    return {
      open : false,
      diaryDetail:'',
    };
  },
methods : {
  weather(w) {
          return require('../../assets/img/weather/'+w+'.png'); 
      }
      ,
  emotion(e) {
          return require('../../assets/img/emotion/'+e+'.png'); 
      }
      ,
  openBook(){

    setTimeout(() => { this.open =true; }, 1000)
   
  },
   albumUrl(url){
       //10197480
       var imgurl = 'https://cdnimg.melon.co.kr/cm/album/images/'+ url.slice(0,3)+'/'+ url.slice(3,5)+'/'+url.slice(5,8)+'/'+url+'_500.jpg';
       return imgurl;
   },
  getDiaryDetail(data){
    console.log(data);
    this.diaryDetail = data ; 
  },
moveWriteDiary(){
    this.$router.push('/writeDiary');
}
},
mounted(){
var winWidth = $(window).width();
var ratio = winWidth / 1920;
var fontSize = {
  small: 12,
  medium: 14
};
var played = [0, 0, 0];
/* var bodyFontSize = Math.max(16 * ratio, 10);
var posX = Math.max(80 * ratio, 30);
$("body").css("font-size", bodyFontSize + "px");
fontSize.small = Math.max(fontSize.small * ratio, 7);
fontSize.medium = Math.max(fontSize.medium * ratio, 10);  */

  
  $(".front:not(.last)").click(function() {
    
    var ix = $(this)
      .parent(".paper")
      .index();
    $(".book").addClass("open");
    $(this)
      .parent(".paper")
      .addClass("open");

   /*  if (!played[ix]) {
      vara[ix].playAll();
      vara[ix].animationEnd(function(i, o) {
        played[ix] = 1;
        if (i == "link") {
          var group = o.container;
          var rect = vara[2].createNode("rect", {
            x: 0,
            y: 0,
            width: o.container.getBoundingClientRect().width,
            height: o.container.getBoundingClientRect().height,
            fill: "transparent"
          });
          group.appendChild(rect);
          $(rect).css("cursor", "pointer");
          $(rect).click(function() {
            console.log(true);
            document.querySelector("#link").click();
          });
        }
      });
    } */
   }); 
  /* $(".back").click(function() {
    if (
      $(this)
        .parent(".paper")
        .index() == 0
    )
      $(".book").removeClass("open");
    $(this) .parent(".paper").removeClass("open");
  }); */

 
  }
}
</script>
