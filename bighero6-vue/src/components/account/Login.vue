<template>
  <div id="login">
    <v-container class="mx-auto" style="max-width:450px; background-color:white; margin-top:150px">
      <div class="inputForm mx-auto">
      <v-form>
        <v-text-field v-model="email"
                      :rules="emailRules"
                      label="email"
                      required
        ></v-text-field>
        <v-text-field
          v-model="password"
          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.required, rules.min]"
          :type="show1 ? 'text' : 'password'"
          name="input-10-1"
          label="Password"
          hint="At least 8 characters"
          counter
          @click:append="show1 = !show1"
        ></v-text-field>
      </v-form>
      <v-row align="center">
        <div align="">
          <v-btn depressed small>로그인</v-btn>
        </div>
      </v-row>
    </div>
    </v-container>
    
  </div>
</template>

<script>
export default {
  data() {
    return {
      password: 'Password',
      email: null,
      show1: false,
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      rules: {
        required: value => !!value || 'Password is Required.',
        min: v => v.length >= 8 || 'Min 8 characters',
      }
    };
  },
  methods: {
  },
};
</script>

<style>
</style>