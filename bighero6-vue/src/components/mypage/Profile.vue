<template>
  <div id ="profile">
  <div class="inputForm mx-auto">
        <div class="profileImg">
          <input ref="imageInput" type="file" hidden @change="changeImage">
          <img id="profileImg" v-if="imgSrc" :src="imgSrc">
          <img id="basicImg" v-else src="../../assets/instagram/adventurealtitude.jpg" />
          

          <div class="profileButton"> 
          <button @click="updateImage"><img src="../../assets/add.png"/></button>&nbsp;
         <!--  <v-file-input hide-input></v-file-input> -->
          <button @click="removeImage"><img src="../../assets/delete.png"/></button>
          </div>
        </div>

    <v-form>
        <v-text-field
        v-model="nickName"
        :counter="10"
        :rules="nameRules"
        label="닉네임"
        required
      ></v-text-field>

      <v-text-field
        v-model="email"
        :rules="emailRules"
        label="이메일"
        required
      ></v-text-field>
    </v-form>   
         <v-btn class="mr-10" tile color="primary" dark  @click="deleteHandler">탈퇴</v-btn>
         <v-btn class="mr-10" tile color="primary" dark @click="updateHandler">수정</v-btn>
      </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        nickName : "빅히어로",
        email:"bigHero@naver.com",
        imgSrc:"",
        file:""
      }
    },
    methods: {
       updateImage() {
      this.$refs.imageInput.click();
    },
    changeImage(e){
      if(e.target.files.length==0) // 사진 등록 안하면 그냥 종료
        return;
      this.file = e.target.files[0]; // 파일을 가져옴
      this.imgSrc = URL.createObjectURL(this.file); // 미리보기용으로 url 생성
    },
    removeImage(){
      this.imgSrc=null;
      this.$refs.imageInput.value="";
    },
    },
       
  }
</script>

<style>
#profile{
    padding:44px;
    text-align: center;
    background-color: #f7f5e0;

}
.inputForm{
    width:550px;
}
.profileImg{
    display: inline-block;
}
.profileImg > img{
    width:300px;
    height: 300px;
    border-radius: 100%;
}

.profileButton {
    text-align: right;
}

.profileButton img{
    width:30px;
    height: 30px;
}

</style>