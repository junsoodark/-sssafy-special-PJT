<template>
<div id ="friend">
  <div id = "friendList">
      <h2>친구 목록</h2><br>

    <v-card
    
      max-width="400"
      class="mx-auto"
    >
      <v-virtual-scroll
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
              <v-icon small>mdi-open-in-new</v-icon>
            </v-list-item-action>
          </v-list-item>

          <v-divider></v-divider>
        </template>
      </v-virtual-scroll>
    </v-card>
  </div>
  <div id="requestList">
      <h2>요청 목록</h2>
    
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
</style>