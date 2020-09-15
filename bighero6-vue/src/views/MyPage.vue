<template>
  <div class="ma-11">
      <h1>마이페이지</h1><v-divider></v-divider><br>
    <v-tabs
      v-model="tab"
      background-color="primary"
      dark 
      grow
    >
      <v-tab
        v-for="item in items"
        :key="item.tab"
      >
        {{ item.tab }}
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="tab">
      <v-tab-item
        v-for="item in items"
        :key="item.tab"
      >
        <v-card flat >
        <div v-if="item.tab == '내정보'">
            <Profile/>
        </div>
        <div v-else>
            
        </div>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
  export default {
 components: {
    Profile: () => import('@/components/mypage/Profile'),
   /*  Friend: () => import('@/components/mypage/Friend'), */
      },
    data () {
      return {
        tab: null,
        items: [
          { tab: '내정보', content: 'Profile' },
          { tab: '친구관리', content: 'Friend' }
        ],
      }
    },
  }
</script>