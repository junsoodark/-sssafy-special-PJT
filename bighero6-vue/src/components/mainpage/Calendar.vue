<template>
  <div class="page-container">
    <!-- <h2 @click="changeDate">Has month and week two mode. And you can custom all style</h2> -->
    <div class="container">
      <Calendar
        class="ui-calendar"
        :dateData="dateData"
        @onMonthChange="onMonthChange"
        :mode="mode"
        @next="onNext"
        @prev="onPrev"
       
        ref="calendar"
      >
        <!-- <div slot="header-left" class="ui-calendar-header__left">
          <button
            :class="['ui-calendar-modeBtn' ,{ active: mode === 'month' }]"
            @click="mode = 'month'"
          >
            Month
          </button>
          <button
            :class="['ui-calendar-modeBtn', { active: mode === 'week' }]"
            @click="mode = 'week'"
          >
            Week
          </button>
        </div> -->
        <template v-slot:body="{ data }">
          <transition :name="transitionName">
            <div class="calendar-body-grid" :key="indentifier">
              <div v-for="(row, index) in data"
                :key="index"
                class="calendar-body-row" v-show="index<=4">
                <div v-for="col in row" 
                  class="calendar-day-item"
                  
                  :key="col.date.full" @click ="getDiaryDetail(col.date.full)">
                  <div
                    :class="[
                      'ui-calendar-item',
                      {
                        'is-otherMonth': col.isPrevMonth || col.isNextMonth,
                        'is-today': col.isToday
                      },
                    ]">
                    <div :slot="col.now" class="ui-calendar-item-date">
                      {{col.date.date}}
                    </div>
                    <div
                      class="ui-calendar-item-name"
                      v-for="(item, index) in col.data"
                      :key="index">

                      <img class="weatherIcon mr-2" :src= "weather(item.weather)">
                      <img class="weatherIcon" :src= "emotion(item.emotion)">
                      <br>
                      <img class="albumIcon" :src="albumUrl(item.music.musicID)"><br>
                      <span>{{item.music.singer}}-{{item.music.title}}</span>
                    </div>
                  </div>
                </div>
                  </div>
            
            </div>
          </transition>
        </template>
      </Calendar>
    </div>
  </div>
</template>

<script>
import 'vue2-event-calendar/dist/vue2-event-calendar.css'
import { Calendar } from 'vue2-event-calendar'
import data from './data'
export default {
  components: {
    Calendar
  },
  data() {
    return {
      indentifier: '',
      //dateData : data().Array
      dateData: [],
      transitionName: 'slide-left',
      mode: 'month',
      diary :{},
    };
  },
  computed :{
       /*  weather : function(){
          return "../../assets/weather/"
          +this.item.weather+".png"; 
     */
  },
  methods: {
    weather(w) {
          return require('../../assets/img/weather/'+w+'.png'); 
      }
      ,
       emotion(e) {
          return require('../../assets/img/emotion/'+e+'.png'); 
      }
      ,
    onMonthChange(val) {
      console.log(val);
      this.indentifier = val.now.full;
      this.getDiaryByMonth(this.indentifier);
    },
    onNext() {
      this.transitionName = 'slide-left';
    },
    onPrev() {
      this.transitionName = 'slide-right';
    },
    changeDate() {
      this.$refs.calendar.changeDate('2017-12-12');
    },
    deleteItem(title) {
      this.dateData = this.dateData.filter(item => item.title !== title);
    },
    renderHeader({ prev, next, selectedDate }) {
      // console.log(selectedDate)
      const h = this.$createElement;
      const prevButton = h('div', {
        class: ['ui-calendar-modeBtn'],
        on: {
          click: prev
        }
      }, ['prev']);
      const nextButton = h('div', {
        class: ['ui-calendar-modeBtn'],
        on: {
          click: next
        }
      }, ['next']);
      const dateText = h('div', { class: ['ui-calendar-modeBtn'] }, [selectedDate]);
      return h('div', [prevButton, dateText, nextButton]);
    },
   getDiaryByMonth(date){
       // 월별 유저의 다이어리 데이터 가져오는 함수 
           date = date.slice(5,7);
           console.log(date)
           //axios 요청 현재월 데이터 요청 

           //this.dateData = {};
   },
   getDiaryDetail(date){
    console.log(date+'테스트');
    var diary = {};
    diary = this.dateData.filter(x=> x.date == date);
    console.log(diary[0])
    this.$emit('showDetail',diary[0]);
   },
   albumUrl(url){
       //10197480
       var imgurl = 'https://cdnimg.melon.co.kr/cm/album/images/'+ url.slice(0,3)+'/'+ url.slice(3,5)+'/'+url.slice(5,8)+'/'+url+'_500.jpg';
       return imgurl;
   }
  },
  mounted() {
    console.log(this.indentifier);
    this.getDiaryByMonth(this.indentifier);
    this.dateData = [{
      date: `2020-09-20`,
      title: 'BTS-DNA',
      weather:'sunny (1)',
      emotion:'happy',
      music : {
          musicID: '10197480',
          title: 'DNA',
          singer: 'BTS'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'친구랑 같이 노래방 간날',
      contentImg:'https://picsum.photos/510/300?1'
    },
    {
      date: `2020-09-21`,
      title: 'BTS-DNA',
      weather:'umbrella (1)',
      emotion:'meh',
      music : {
          musicID: '10074454',
          title: '비도오고그래서',
          singer: '헤이즈'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘은 비가 너무 많이 왔다.',
      contentImg:'https://picsum.photos/510/300?2'
    },{
      date: `2020-09-22`,
      title: 'BTS-DNA',
      weather:'sunny (1)',
      emotion:'happy',
       music : {
          musicID: '10197480',
          title: 'DNA',
          singer: 'BTS'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘은 날이 너무 좋다. 노래방 가고싶다 ㅎㅎㅎ',
      contentImg:'https://picsum.photos/510/300?3'
    },
     {
      date: `2020-09-14`,
      title: '트와이스-cheerup',
      weather:'cloud (1)',
      emotion:'smile',
      music : {
          musicID: '10238683',
          title: '벌써12시',
          singer: '청하'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다',
      contentImg:'https://picsum.photos/510/300?4'
    },
    {
      date: `2020-09-11`,
      title: '여행-볼빨간사춘기',
      weather:'windy (1)',
      emotion:'meh',
      music : {
          musicID: '10170069',
          title: '여행',
          singer: '볼빨간 사춘기'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'해외든 국내든 여행가고싶다ㅜㅜ 노래로 대신 여행기분 느껴야지',
      contentImg:'https://picsum.photos/510/300?5'
    },
    {
      date: `2020-09-02`,
      title: '마마무-데칼코마니',
      weather:'storm (1)',
      emotion:'sad',
      music : {
          musicID: '10012900',
          title: '데칼코마니',
          singer: '마마무'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'벌써 가을이다. 저녁되면 쌀쌀해지는듯 ㅜㅜ',
      contentImg:'https://picsum.photos/510/300?5'
    },
    {
      date: `2020-09-17`,
      title: '트와이스-cheerup',
      weather:'storm (1)',
      emotion:'smile',
      music : {
          musicID: '10096855',
          title: '가을아침',
          singer: '아이유'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다',
      contentImg:'https://picsum.photos/510/300?6'
    },
    {
      date: `2020-09-18`,
      title: '트와이스-cheerup',
      weather:'storm (1)',
      emotion:'smile',
      music : {
          musicID: '10001952',
          title: '숨',
          singer: '박효신'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘 구름은 많이 꼈지만 친구만나서 기분 좋아짐 ;)',
      contentImg:'https://picsum.photos/510/300?6'
    },
    {
      date: `2020-09-08`,
      title: '트와이스-cheerup',
      weather:'umbrella (1)',
      emotion:'smile',
      music : {
          musicID: '10001952',
          title: '숨',
          singer: '박효신'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘 아무것도 안해서 심심했다.',
      contentImg:'https://picsum.photos/510/300?6'
    },
    {
      date: `2020-09-09`,
      title: '트와이스-cheerup',
      weather:'umbrella (1)',
      emotion:'smile',
      music : {
          musicID: '10217134',
          title: '너를 만나',
          singer: '폴킴'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다',
      contentImg:'https://picsum.photos/510/300?6'
    },
    {
      date: `2020-08-21`,
      title: 'BTS-DNA',
      weather:'sunny (1)',
      emotion:'happy',
      music : {
          musicID: '10197480',
          title: 'DNA',
          singer: 'BTS'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'친구랑 같이 노래방 간날',
      contentImg:'https://picsum.photos/510/300?1'
    },
    {
      date: `2020-08-22`,
      title: 'BTS-DNA',
      weather:'umbrella (1)',
      emotion:'meh',
      music : {
          musicID: '10074454',
          title: '비도오고그래서',
          singer: '헤이즈'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘은 비가 너무 많이 왔다.',
      contentImg:'https://picsum.photos/510/300?2'
    },{
      date: `2020-08-01`,
      title: 'BTS-DNA',
      weather:'sunny (1)',
      emotion:'happy',
       music : {
          musicID: '10197480',
          title: 'DNA',
          singer: 'BTS'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘은 날이 너무 좋다. 노래방 가고싶다 ㅎㅎㅎ',
      contentImg:'https://picsum.photos/510/300?3'
    },
     {
      date: `2020-08-15`,
      title: '트와이스-cheerup',
      weather:'cloud (1)',
      emotion:'smile',
      music : {
          musicID: '10238683',
          title: '벌써12시',
          singer: '청하'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다',
      contentImg:'https://picsum.photos/510/300?4'
    },
    {
      date: `2020-08-12`,
      title: '여행-볼빨간사춘기',
      weather:'windy (1)',
      emotion:'meh',
      music : {
          musicID: '10170069',
          title: '여행',
          singer: '볼빨간 사춘기'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'해외든 국내든 여행가고싶다ㅜㅜ 노래로 대신 여행기분 느껴야지',
      contentImg:'https://picsum.photos/510/300?5'
    },
    {
      date: `2020-08-02`,
      title: '마마무-데칼코마니',
      weather:'storm (1)',
      emotion:'sad',
      music : {
          musicID: '10012900',
          title: '데칼코마니',
          singer: '마마무'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'벌써 가을이다. 저녁되면 쌀쌀해지는듯 ㅜㅜ',
      contentImg:'https://picsum.photos/510/300?5'
    },
    {
      date: `2020-08-17`,
      title: '트와이스-cheerup',
      weather:'storm (1)',
      emotion:'smile',
      music : {
          musicID: '10096855',
          title: '가을아침',
          singer: '아이유'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다',
      contentImg:'https://picsum.photos/510/300?6'
    },
    {
      date: `2020-08-30`,
      title: '트와이스-cheerup',
      weather:'storm (1)',
      emotion:'smile',
      music : {
          musicID: '10001952',
          title: '숨',
          singer: '박효신'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘 구름은 많이 꼈지만 친구만나서 기분 좋아짐 ;)',
      contentImg:'https://picsum.photos/510/300?6'
    },
    {
      date: `2020-08-05`,
      title: '트와이스-cheerup',
      weather:'umbrella (1)',
      emotion:'smile',
      music : {
          musicID: '10001952',
          title: '숨',
          singer: '박효신'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘 아무것도 안해서 심심했다.',
      contentImg:'https://picsum.photos/510/300?6'
    },
    {
      date: `2020-08-09`,
      title: '트와이스-cheerup',
      weather:'windy (1)',
      emotion:'smile',
      music : {
          musicID: '10217134',
          title: '너를 만나',
          singer: '폴킴'
      },
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg',
      content:'오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다',
      contentImg:'https://picsum.photos/510/300?6'
    },
    ]

  }
};

</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Rubik&display=swap'); 
*{
    font-family: 'Rubik', sans-serif; 
}
/* .ui-calendar-item-date{
     font-family: 'Rubik', sans-serif; 
} */
.calendar-day-item{
    height:105px;
    width:25px;
    cursor: pointer;
     
}
.albumIcon {
    width: 40px;
    height: 30px;
}
.weatherIcon{
    width:18px;
    height: 18px;
    float:left;
}
.ui-calendar-item-name{
     text-align: center; 
     padding:0;
     margin:0;
}
span{
    font-size: 4px;
}

</style>

