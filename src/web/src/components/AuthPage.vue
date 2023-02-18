<template>
  <section class="section" :class="{ active: status }">
    

    <div class="container" :class="{ active: status }">

      <!-- LOGIN IN -->
      <div class="user signinBx">
        <div class="imgBx">
          <img class="img" src="@/assets/lock-new.png" alt="">
          <div class="formBx">
            <form action="#" @submit.prevent="login({ email: signinMail, password: inPass })">
              <h2>Sign In</h2>
              <input type="text" placeholder="Email Adress" v-model="signinMail">
              <input type="password" placeholder="Password" v-model="inPass">
              <button type="submit">Login</button>
              <p class="signup">
                don't have an account?
                <a href="#" @click.prevent="toggleForm(true)">Sign up.</a>
              </p>
            </form>
          </div>
        </div>
      </div>


      <!-- REGISTRATION -->
      <div class="user signupBx">
        <div class="imgBx">
          <div class="formBx">
            <form action="#" @submit.prevent="signup({ name: upName, email: upEmail, password: upPass })">
              <h2>Create an account</h2>
              <input type="text" placeholder="Username" v-model="upName">
              <input type="email" placeholder="Email Adress" v-model="upEmail">
              <input type="password" placeholder="Password" v-model="upPass">
              <button type="submit">Sign Up</button>
              <p class="signup">
                Already have an account?
                <a href="#" @click.prevent="toggleForm(false)">Sign in.</a>
              </p>
            </form>
          </div>
          <img class="img" src="@/assets/keys.jpg" alt="">
        </div>
      </div>

    </div>

  </section>
</template>

<style scoped lang='scss'>
section {
  position: relative;
  min-height: 100vh;
  background: #557085;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  transition: .5s;
}

.container {
  position: relative;
  width: 800px;
  height: 500px;
  background: #fff;
  box-shadow: 0 15px 50px rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.user {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: nowrap;
}

.imgBx {
  position: absolute;
  width: 100%;
  height: 100%;
  transition: .5s;
  display: flex;
}

.img {
  width: 50%;
  height: 100%;
  object-fit: cover;
}

.formBx {
  position: relative;
  width: 50%;
  height: 100%;
  background: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px;
  transition: .5s;
}

.formBx form h2 {
  font-size: 18px;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 2px;
  text-align: center;
  width: 100%;
  margin-bottom: 10px;
  color: #555;
}

.formBx form input {
  width: 100%;
  padding: 10px;
  background: #f5f5f5;
  color: #333;
  border: none;
  outline: none;
  box-shadow: none;
  font-size: 14px;
  margin: 8px;
  letter-spacing: 1px;
  font-weight: 300;
}

.formBx form button {
  width: 100px;
  height: 36px;
  background: #667eff;
  color: white;
  cursor: pointer;
  border: none;
  outline: none;
  margin: 8px;
  font-size: 14px;
  font-weight: 500;
  letter-spacing: 1px;
  transition: .5s;
}

.signup {
  position: relative;
  margin-top: 20px;
  margin-left: 8px;
  font-size: 12px;
  letter-spacing: 1px;
  color: #555;
  text-transform: uppercase;
  font-weight: 300;
}

.signup a {
  font-weight: 600;
  color: #667eff;
  text-decoration: none;
}

.signupBx .imgBx .formBx form button {
  background: #e73e49;
}

.signupBx .signup a {
  color: #e73e49;
}

.signupBx {
  pointer-events: none;
}

.container.active .signupBx {
  pointer-events: initial;
}

.signupBx .formBx {
  top: -100%;
  left: 0;
}

.container.active .signupBx .formBx {
  top: 0;
}

.signupBx .imgBx {
  position: relative;
}

.signupBx .imgBx .img {
  position: absolute;
  left: 50%;
  top: 100%;
  transition: .5s;
}

.container.active .signupBx .imgBx .img {
  top: 0;
}

.container .signinBx .formBx {
  top: 0;
}

.container.active .signinBx .formBx {
  top: -200%;
}

.container .signinBx .imgBx {
  top: 0;
}

.container.active .signinBx .imgBx {
  top: 100%;
}

.section.active {
  background: #8E969B;
}
</style>

<script>
  import { mapActions } from 'vuex';

  export default {
    components: {  },
    data: function() {
      return {
        status: false,

        signinMail: '',
        inPass: '',

        upName: '',
        upEmail: '',
        upPass: '',
      }
    },
    methods: {
      toggleForm: function(bool) {
        this.status = bool;
      },
      ...mapActions({ signup: 'signup', login: 'login' }),
    },
  }
</script>
