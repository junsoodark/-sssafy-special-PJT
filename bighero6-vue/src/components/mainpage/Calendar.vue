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
                  
                  :key="col.date.full">
                  <div
                    :class="[
                      'ui-calendar-item',
                      {
                        'is-otherMonth': col.isPrevMonth || col.isNextMonth,
                        'is-today': col.isToday
                      },
                    ]">
                    <div class="ui-calendar-item-date">
                      {{col.date.date}}
                    </div>
                    <div
                      class="ui-calendar-item-name"
                      v-for="(item, index) in col.data"
                      :key="index">

                      <img class="weatherIcon mr-2" :src= "weather(item.weather)">
                      <img class="weatherIcon" src= "../../assets/emotion/happy.png">
                      <br>
                      <img class="albumIcon" :src="item.album" ><br>
                      <span>{{item.title}}</span>
                      
                      <!-- <span class="del" @click="deleteItem(item.title)">✖️</span> -->
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
      dateData: data().Array,
      transitionName: 'slide-left',
      mode: 'month'
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
          return require('../../assets/weather/'+w+'.png'); 
      }
      ,
    onMonthChange(val) {
      console.log(val);
      this.indentifier = val.now.full;
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
    }
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
    font-size: 5px;
}
</style>

