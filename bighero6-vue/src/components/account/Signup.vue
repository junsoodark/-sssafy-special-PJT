<template>
  <div id="login">
    <v-container class="mx-auto" style="max-width:450px; margin-top:30px">
      <div class="inputForm mx-auto">
      <v-form>
        <v-row align="center">
            <v-col cols="100" sm="10">
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
            </v-col>
        </v-row>
        <v-text-field v-model="email"
                      :rules="emailRules"
                      label="email"
                      required
        ></v-text-field>
        <v-btn depressed small color = "primary">로그인</v-btn>
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
        <v-col cols="12" sm="7">
        </v-col>
        <v-col cols="12" sm="2" class="my-5">
            <v-btn depressed small color = "primary">로그인</v-btn>
        </v-col>
        <v-col cols="12" sm="3" class="my-5">
            <v-btn depressed small color = "primary">회원가입</v-btn>
        </v-col>
      </v-row>
    </div>
    </v-container>
    
  </div>
</template>

<script>
export default {
  data() {
    return {
         bottomNav: 'recent',
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