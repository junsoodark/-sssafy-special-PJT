import Vue from 'vue'
import Vuex from 'vuex'
import Axios from "axios";
import router from "@/router";
import VueCookies from "vue-cookies";

import createPersistedState from "vuex-persistedstate";
import moduleName from "./test_moduleName";

Vue.use(Vuex)

const modules = {
  moduleName,
};
const plugins = [
  createPersistedState({
    paths: ["moduleName"],
  }),
];
const API_URL = process.env.VUE_APP_LOCAL_URL;

export default new Vuex.Store({
  state: {
    articles: require('@/data/articles.json'),
    authToken: VueCookies.get("auth-token"),
    drawer: false,
    items: [
      {
        text: 'Home',
        href: '/',
      },
      {
        text: 'Diary',
        href: 'diary',
      },
      {
        text: 'Playlist',
        href: 'playlist',
      },
      {
        text: 'MyPage',
        href: 'mypage',
      },
      {
        text: 'login',
        href: 'login',
      },
    ],
  },
  getters: {
    // auth
    isLoggedIn: (state) => !!state.authToken,
    // auth, articles
    config: (state) => ({
      headers: { Authorization: `Token ${state.authToken}` },
    }),
 /*    categories: state => {
      const categories = []

      for (const article of state.articles) {
        if (
          !article.category ||
          categories.find(category => category.text === article.category)
        ) continue

        const text = article.category

         categories.push({
          text,
          href: '',
        }) 
      }

      return categories.sort().slice(0, 4)
    }, */
    //links: (state, getters) => {
      links: (state) => {
      //return state.items.concat(getters.categories)
      return state.items
    },
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.authToken = token;
      VueCookies.set("auth-token", token);
    },
    setDrawer: (state, payload) => (state.drawer = payload),
    toggleDrawer: state => (state.drawer = !state.drawer),
  },
  actions: {
    login({ commit }, loginData) {
      const params = {
        'email' : loginData.email,
        'password' : loginData.password
      }
      var JsonForm = JSON.stringify(params)
      Axios({method:'POST',url:`${API_URL}login`,params:params,data:JsonForm,headers:{'Content-Type': 'application/json; charset=utf-8'}})
      .then(res => {
        commit('SET_TOKEN', res.data.token)
        commit('UPDATE_EMAIL', loginData.email)

        sessionStorage.setItem('jwt-auth-token', res.data.token);
        sessionStorage.setItem('user-email', loginData.email);

        // 로그인 시간 저장
        let date = new Date()
        var loginH = date.getHours()
        var loginM = date.getMinutes()
        var loginS = date.getSeconds()

        if (loginH < 10) {
          loginH = '0' + loginH
        }
        if (loginM < 10) {
          loginM = '0' + loginM
        }
        if (loginS < 10) {
          loginS = '0' + loginS
        }

        const loginTime = loginH + ":" + loginM + ":" + loginS
        commit('UPDATE_LOGIN_TIME', loginTime)

        
        
        alert("로그인")

        router.push({ name: 'Home' })
      })
      .catch(err => {
        alert(err.response.data)
      })
    },


    signup({ dispatch }, { code, email, nickname, password }) {
      var params = new URLSearchParams();
      params.append("code", code);
      var form = {
        email: email,
        name: nickname,
        password: password,
      };
      var JsonForm = JSON.stringify(form);
      Axios({
        method: "POST",
        url: `${API_URL}user/signUp`,
        params: params,
        data: JsonForm,
        headers: { "Content-Type": "application/json; charset=utf-8" },
      })
      .then((res) => {
        //
        const loginData = {
          'email': email,
          'password': password,
        }
        
        dispatch("initSignUp", loginData)

        //
        alert(res.data.msg);
        // router.push({ name: "Login" });
      })
      .catch((err) => {
        alert(err.response.data);
        console.log(err);
      });
    },


    logout({ commit }) {
      commit("SET_TOKEN", null); // state 에서도 삭제
      VueCookies.remove("auth-token"); // cookie 에서는 삭제
      
      localStorage.clear();
      sessionStorage.clear();

      alert("로그아웃");
      router.push({ name: "Home" });
    },


  },
})
