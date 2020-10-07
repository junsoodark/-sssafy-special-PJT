<template>
<div>
  <span v-if="isMy">마이플레이리스트 -</span>
  <span v-else>월별플레이리스트 -</span>
  {{playlistTitle}}
<v-system-bar window  dark>
    

        <v-icon class="ml-4" light>headset</v-icon>




    <v-spacer></v-spacer>
 MUSIC PLAYER
    <v-spacer></v-spacer>
    <v-icon>remove</v-icon>
    <v-icon>check_box_outline_blank</v-icon>
    <v-icon>close</v-icon>
  </v-system-bar>
</div>
</template>

 <script>
export default {
    props: {
      playlistTitle: String,
      isMy : Boolean
    },

mounted:{
}
}
</script>

