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
    authToken: VueCookies.get("auth-token"),
    refreshToken: VueCookies.get("refresh-token"),
    email: VueCookies.get("email"),
    id: VueCookies.get("id"),
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
      headers: { Authorization: `Bearer ${state.authToken}`,
      },
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
    UPDATE_EMAIL(state, email){
      state.email = email;
      VueCookies.set("email", email);
    },
    UPDATE_ID(state, id){
      state.id = id;
      VueCookies.set("id", id);
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
      Axios({method:'POST',url:`http://localhost:8080/api/account/login`,params:params,data:JsonForm,headers:{'Content-Type': 'application/json; charset=utf-8'}})
      .then(res => {
        commit('SET_TOKEN', res.data.accessToken)
        commit('UPDATE_EMAIL', loginData.email)

        sessionStorage.setItem('jwt-auth-token', res.data.accessToken);
        sessionStorage.setItem('user-email', loginData.email);
        sessionStorage.setItem('jwt-refresh-token', res.data.refreshToken);
        sessionStorage.setItem('user-id', res.data.userId);

        console.log(sessionStorage.getItem('user-email'));
        console.log(sessionStorage.getItem('jwt-auth-token'));

        alert("로그인")
        
        router.push({ name: 'home' })
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
      router.push({ name: "home" });
    },


  },
})
