<template>
  <div class="diary">
    <div class="back page"></div>
    <div class="current page">
      <div class="header">
        <v-menu
          v-model="menu"
          :close-on-content-click="false"
          :nudge-right="40"
          transition="scale-transition"
          offset-y
          min-width="290px"
        >
          <template v-slot:activator="{ on, attrs }">
            <p class="today">
              <v-icon style="cursor:pointer;"
                readonly
                v-bind="attrs"
                v-on="on"
              >event</v-icon>
              {{ date }}
            </p>
          </template>
          <v-date-picker v-model="date" @input="menu = false"></v-date-picker>
        </v-menu>
        <div style="text-align:left;">
          <span class="setting">날씨</span>
          <img class="settingimg" src="@/assets/img/weather/sunny.png" />
          <img class="settingimg" src="@/assets/img/weather/cloudy-day.png" />
          <img class="settingimg" src="@/assets/img/weather/cloud.png" />
          <img class="settingimg" src="@/assets/img/weather/windy.png" />
          <img class="settingimg" src="@/assets/img/weather/umbrella (1).png" />
          <img class="settingimg" src="@/assets/img/weather/storm.png" />
          <img class="settingimg" src="@/assets/img/weather/snowing.png" />
        </div>
        <div style="text-align:left;">
          <span class="setting">기분</span>
          <img class="settingimg" src="@/assets/img/emotion/smile.png" />
          <img class="settingimg" src="@/assets/img/emotion/happy.png" />
          <img class="settingimg" src="@/assets/img/emotion/meh (1).png" />
          <img class="settingimg" src="@/assets/img/emotion/crying.png" />
          <img class="settingimg" src="@/assets/img/emotion/sad.png" />
        </div>
        <div class="ma-3">
          <v-btn block color="secondary" dark>사진 수정하기<v-icon dark right>mdi-camera-plus</v-icon></v-btn>
          <v-img src="https://picsum.photos/510/300?random" aspect-ratio="2.0"></v-img>
        </div>
        <v-container style="padding-top: 0px;padding-bottom: 5px;">
          <v-row>
            <v-col cols="12" style="padding-top: 0px;">
              <v-sheet elevation="3" class="pa-2">
                <v-textarea
                  v-model="diarytext"
                  :auto-grow="true"
                  :rounded="true"
                ></v-textarea>
              </v-sheet>
            </v-col>
          </v-row>
        </v-container>
        <v-container class="px-3" fluid style="padding-top: 0px;">
          <v-row>
            <v-col>
              <v-switch style="margin-top: 0px;padding-top: 0px;"
                v-model="switch1"
                :label="`공개 설정 : ${switch1.toString()}`"
              ></v-switch>
            </v-col>
            <v-col>
              <v-btn small color="error" style="float:right;">저장</v-btn>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </div>
    <div class="next page"></div>
    <div style="float:right;">
      <!--<div class="cd-player">
        <div class="cd-player-inner">
          <div class="play-cover">

          </div>
          <div class="cd">
            <div class="album-cover spin">
              <img src="https://d3rt1990lpmkn.cloudfront.net/unbranded/d83a086148afe6539dae524607cbe4b6ccdd0360" alt="">

            </div>
            <div class="song-info">
              <h2 class="song-title"></h2>
              <h3 class="song-singer"> </h3>
              <h4 class="song-hits"></h4>
            </div>
          </div>
        </div>
      </div>-->
      <div style="margin-right:30px;">
        <v-sheet
          class="mx-auto"
          elevation="8"
          max-width="800"
        >
          <h2 style="padding-top:20px;padding-left:30px;">이런 노래는 어떠세요?</h2>
          <v-slide-group
            v-model="model"
            class="pa-4"
            center-active
            show-arrows
          >
            <v-slide-item
              v-for="n in 15"
              :key="n"
              v-slot:default="{ active, toggle }"
            >
              <v-card
                :color="active ? 'primary' : 'grey lighten-1'"
                class="ma-4"
                height="200"
                width="100"
                @click="toggle"
              >
                <v-row
                  class="fill-height"
                  align="center"
                  justify="center"
                >
                  <img src="@/assets/instagram/sunset.jpg" style="width:90px;" />
                  <div>
                    <h4><v-icon small left>mdi-music</v-icon>헤이즈</h4>
                    <h4>비도 오고 그래서</h4>
                    <span>1st mini album</span>
                  </div>
                </v-row>
              </v-card>
            </v-slide-item>
          </v-slide-group>
        </v-sheet>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    date: new Date().toISOString().substr(0, 10),
    menu: false,
    diarytext: "",
    switch1: true,
    model: null,
  }),
}
</script>

<style scoped>
.group {
  display: flex;
  flex: 1;
  justify-content: space-around;
}
.diary{
  margin-top:50px;
  position:relative;
  margin-left:100px;
  font-size:14px;
}
.page{  
  overflow:hidden;
  transform-origin: 0% 0%;
  position:absolute;
  width:400px;
  height:600px;
  box-shadow:0 0 3px 1px rgba(0,0,0,.3); 
  border-top-left-radius:2px;
  border-bottom-left-radius:2px;
  border-top-right-radius:12px;
  border-bottom-right-radius:12px; 
}
.current{
  background:white;
  z-index:10;
  background-position:-15px -13px;
  background-size:18px 18px;
  background-image: linear-gradient(45deg, rgba(0,0,0,.2) 1px,white 1px);
  transform:rotateY(8deg);
  box-shadow:0 0 3px 1px rgba(0,0,0,.3), inset 2px 0 3px -1px rgba(0,0,0,.3); 
}

.back{
  transform:rotateY(180deg);
  background:#fffdeb;
}
.next{
  background:#fffde8;
}

.title{
  border:0px;
  text-align: left;
}

/* current page */
.diary .current .header{
  margin-top:10px;
  text-align:center;
  font-family:sans-serif;
  position:relative;
  width:100%;
}
.today{
  font-weight:bold;
}
.header a{  
  position:absolute;
  color:#ff7055;
}
.yesterday{
  left:10px;
}
.tomorrow{
  right:10px;
}

.setting {
  font-size: 18px;
  padding-left:15px;
  padding-right:8px;
  vertical-align:top;
}
.settingimg {
  width:30px;
  padding-right:10px;
  cursor: pointer;
}


</style>