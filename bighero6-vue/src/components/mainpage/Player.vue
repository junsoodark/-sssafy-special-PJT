<template>

<div>
    <v-btn @click="playVideo">play</v-btn>
    <v-btn @click="pauseVideo">pause</v-btn>
    <v-btn @click="stopVideo">stop</v-btn>
    <youtube :video-id="videoId" ref="youtube" @playing="playing" style="width:0;height:0;"></youtube>
   
    
</div>
    
</template>

<script>
import Vue from 'vue'
import VueYoutube from 'vue-youtube'
 
Vue.use(VueYoutube)

export default {
  data() {
    return {
      videoId: '7-x3uD5z1bQ'
    }
  },
  methods: {
    playVideo() {
      this.player.playVideo()
       console.log(this.getId("https://www.youtube.com/watch?v=7-x3uD5z1bQ"))
    },
      pauseVideo() {
      this.player.pauseVideo()
    },
    stopVideo() {
        this.player.stopVideo()
    },
    playing() {
      console.log('\o/ we are watching!!!')
    }
    , 
    getId (url) {
      return this.$youtube.getIdFromUrl(url)
    }
  },
  computed: {
    player() {
      return this.$refs.youtube.player
    }
  }
}
</script>

<style>


</style>
