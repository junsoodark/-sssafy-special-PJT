<template>
  <div class="diary">
    <div class="back page"></div>
    <div class="current page">
      <div class="header">
        <a href="#" class="yesterday">< 2016-07-14</a>      
        <a href="#" class="tomorrow">2016-07-16 ></a>
        <p class="today">2016-07-15 (Friday)</p>
        <v-container fluid>
    <v-row justify="space-around" class="mb-2">
      <span class="group pa-2">
        <v-icon>home</v-icon>
        <v-icon>event</v-icon>
        <v-icon>info</v-icon>
      </span>
    </v-row></v-container>
        <input type="text" class="title"/>
      </div>
    </div>
    <div class="next page"></div>
  </div>
</template>

<script>
export default {

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
  height:500px;
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
</style>