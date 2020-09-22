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
        <v-container>
          <v-row>
            <v-col cols="12">
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
      </div>
    </div>
    <div class="next page"></div>
  </div>
</template>

<script>
export default {
  data: () => ({
    date: new Date().toISOString().substr(0, 10),
    menu: false,
    diarytext: "",
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