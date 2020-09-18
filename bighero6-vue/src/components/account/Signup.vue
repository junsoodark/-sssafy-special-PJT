<template>
  <div id="login">
    <v-container class="mx-auto" style="max-width:600px; margin-top:50px">
      <div class="inputForm mx-auto">
      <v-form>
        <v-row align="center" no-gutters>
            <v-col cols="12" sm="9">
                <v-text-field v-model="email"
                      :rules="emailRules"
                      label="email"
                      required
                 ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
            </v-col>
            <v-col cols="12" sm="2">
              <v-btn depressed small color = "primary">인증코드 발송</v-btn>
            </v-col>
        </v-row>
        <v-text-field v-model="certicode"
              label="인증코드"
              required
        ></v-text-field>
        <v-text-field v-model="nickname"
                      :rules="[rules.nicknamerequired, rules.max]"
                      label="nickname"
                      required
        ></v-text-field>
        <v-text-field
          v-model="password"
          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.passwordrequired, rules.min]"
          :type="show1 ? 'text' : 'password'"
          name="input-10-1"
          label="Password"
          hint="At least 8 characters"
          counter
          @click:append="show1 = !show1"
        ></v-text-field>
        <v-text-field
          v-model="reenter"
          :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="reenterRules"
          :type="show2 ? 'text' : 'password'"
          name="input-10-1"
          label="Re-enter"
          hint="At least 8 characters"
          counter
          @click:append="show2 = !show2"
        ></v-text-field>
      </v-form>
      <div align="right" class="my-4">
        <v-btn depressed small color = "primary">회원가입</v-btn>
      </div>
    </div>
    </v-container>
    
  </div>
</template>

<script>
export default {
  data() {
    return {
      password: 'Password',
      reenter: '',
      certicode: '',
      email: '',
      show1: false,
      show2: false,
      nickname: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      reenterRules: [
        v => v== this.password || 'Password is not equals',
        v => !!v || 'Re-enter is required',
      ],
      rules: {
        passwordrequired: value => !!value || 'Password is required.',
        nicknamerequired: value => !!value || 'Nickname is required',
        min: v => v.length >= 8 || 'Min 8 characters',
        max: v => v.length <= 10 || 'Max 10 characters',
      }
    };
  },
  methods: {
  },
};
</script>

<style>
</style>