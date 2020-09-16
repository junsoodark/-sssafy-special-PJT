<template>
<div id ="friend">
  <div id = "friendList">
      <div class="friendListTitle mt-0">
        <h2 class="friendListText">친구 목록</h2><v-icon large  class="addUserBnt ml-6" @click="dialog=true">mdi-account-plus</v-icon>
      </div>
      <!-- 유저 검색 모달 창 -->
      <v-dialog 
        v-model="dialog"
        max-width="600px"
        primary
      >
        <v-card class="modal px-10">
          <v-card-title>
            추가하고 싶은 친구 닉네임으로 검색해주세요
          </v-card-title>
          <v-card-content>
      <v-text-field
        flat
        hide-details
        label="Search"
        prepend-inner-icon="mdi-magnify"
        solo-inverted
      ></v-text-field>
      <v-virtual-scroll class="mt-4"
        :items="items"
        height="400"
        item-height="64"
      >
        <template v-slot="{ item }">
          <v-list-item >
            <v-list-item-avatar>
                <img :src="item.avatar">
              </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>
                 <strong>{{ item.title }}</strong>
              </v-list-item-title>
            </v-list-item-content>

            <v-list-item-action>
              <v-btn tile small color="primary">친구요청</v-btn>
            </v-list-item-action>
          </v-list-item>

          <v-divider></v-divider>
        </template>
      </v-virtual-scroll>

     

           
          </v-card-content>
          <v-card-actions>
            <v-btn style="float:right;"
              color="primary"
              text
              @click="dialog = false"
            >
              Close
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
        

      <v-text-field
      class="friendSearch mx-auto mb-5"
        flat
        hide-details
        label="Search"
        prepend-inner-icon="mdi-magnify"
        solo-inverted
      ></v-text-field>

    <v-card
      max-width="600"
      class="mx-auto"
    >
      <v-virtual-scroll
        :items="items"
        height="500"
        item-height="64"
      >
        <template v-slot="{ item }">
          <v-list-item >
            <v-list-item-avatar>
                <img :src="item.avatar">
              </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>
                 <strong>{{ item.title }}</strong>
              </v-list-item-title>
            </v-list-item-content>

            <v-list-item-action>
              <v-icon small>mdi-open-in-new</v-icon>
            </v-list-item-action>
          </v-list-item>

          <v-divider></v-divider>
        </template>
      </v-virtual-scroll>
    </v-card>
  </div>
  <div id="requestList">
      <h2 class="mt-7 mb-7">요청 목록</h2>
       <v-card
      max-width="600"
      class="mx-auto"
    >
      <v-virtual-scroll
        :items="items"
        height="500"
        item-height="64"
      >
        <template v-slot="{ item }">
          <v-list-item >
            <v-list-item-avatar>
                <img :src="item.avatar">
              </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>
                 <strong>{{ item.title }}</strong>
              </v-list-item-title>
            </v-list-item-content>

            <v-list-item-action class ="requestAccept">
              <v-btn tile x-small color="primary" class="mr-2">수락</v-btn>
              <v-btn tile x-small color="error">거절</v-btn>
            </v-list-item-action>
          </v-list-item>

          <v-divider></v-divider>
        </template>
      </v-virtual-scroll>
    </v-card>
  </div>
</div>
</template>

<script>
  export default {
    data () {
      return {
           items: [
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          title: 'Brunch this weekend?',
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          title: 'Summer BBQ',
        },

        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
          title: 'Oui oui',
        },
       
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
          title: 'Birthday gift',
        },
      
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
          title: 'Recipe to try',
        },
         {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          title: 'Brunch this weekend?',
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          title: 'Summer BBQ',
        },

        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
          title: 'Oui oui',
        },
       
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
          title: 'Birthday gift',
        },
      
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
          title: 'Recipe to try',
        }
      ],
      dialog : false
      }
    },
    methods: {
  
    },
    computed: {
       items () {
        return Array.from({ length: this.length }, (k, v) => v + 1)
      },
      length () {
        return 30
      }, 
    },
       
  }
</script>

<style>
#friend{
    width:100%;
    display: inline-block;
 
}
#friend > div{
    width: 50%;
    height: 600px;
    text-align: center;
    padding: 30px 100px;
}
  #friendList{
    float: left;
}   
 #requestList{
    float: left;
} 
.friendSearch{
  width:70%;
}
.friendListTitle{
  display: inline-block;
}
.friendListText{
  float: left;
}
.requestAccept{
  display: inline;
}
.modal{
  text-align: center;
}
</style>