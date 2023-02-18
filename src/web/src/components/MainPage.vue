<template>
  <section class="main">
    <button class="login" @click="logoutF()">
      Log out
    </button>
    <button class="create" @click="createStatus=true">
      Create org
    </button>
    <button v-if="createStatus" class="form-close" @click.prevent="createStatus=false">close</button>
    <form action="#" class="create__form" :class="{ is_active: createStatus }" @submit.prevent="createOrg">
      <div class="form-container">
        <input type="text" class="create__input" placeholder="Название" v-model="formName">
        <input type="text" class="create__input" placeholder="ИНН" v-model="formINN">
        <input type="text" class="create__input" placeholder="Подразделение" v-model="formSubdiv">
        <input type="text" class="create__input" placeholder="КПП" v-model="formKPP">
        <button type="submit" class="create__btn">
          Создать
        </button>
      </div>
    </form>
    <div class="container">
      <h2 class="heading">Home</h2>
      <ul class="list">
        <li class="item" v-for="item in cards" :key="cards.indexOf(item)">

        </li>
      </ul>
    </div>
  </section>
</template>

<style scoped lang='scss'>
  .main {
    position: relative;
    min-height: 100vh;
    background: #557085;
  }

  .login {
    position: absolute;
    top: 50px;
    right: 30px;
    color: white;
    background: none;
    border: none;
    cursor: pointer;
    font-size: 20px;
  }

  .create {
    position: absolute;
    top: 80px;
    right: 30px;
    color: white;
    background: none;
    border: none;
    cursor: pointer;
    font-size: 20px;
  }

  .create__form {
    position: absolute;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;

    display: flex;
    align-items: center;
    justify-content: center;

    background: rgba(black, .4);

    display: none;

    &.is_active {
      display: flex;
    }
  }

  .form-close {
    position: absolute;
    right: 700px;
    top: 120px;
    font-size: 30px;
    color: white;
    background: none;
    border: none;
    cursor: pointer;
    z-index: 100;
  }

  .form-container {
    display: flex;
    flex-direction: column;
    gap: 20px;

    padding: 50px 30px;
    background: #fff;
    border-radius: 10px;
  }

  .create__input {
    border: none;
    background: none;
    outline: none;

    padding: 10px 20px;
    border-radius: 10px;
    background: rgba(black, .1);
    font-size: 20px;
  }

  .create__btn {
    background: none;
    border: none;
    cursor: pointer;

    padding: 10px 20px;
    border-radius: 10px;
    background: rgba(black, .1);
    font-size: 20px;
  }

  .container {
    max-width: 1680px;
    padding: 0 15px;
    margin: 0 auto;
  }

  .heading {
    font-weight: 400;
    padding-top: 100px;
    color: white;
    font-size: 32px;
  }

  .list {
    padding-top: 50px;
    display: flex;
    flex-direction: column;
    gap: 20px;
  }

  .item {
    background: #fff;
    border-radius: 10px;
    min-height: 100px;
  }
</style>

<script>
  import axios from 'axios'
  import { mapActions } from 'vuex';

  export default {
    components: {  },
    data: function() {
      return {
        createStatus: false,

        cards: [],

        formName: '',
        formINN: '',
        formSubdiv: '',
        formKPP: '',
      }
    },
    methods: {
      getCards: function() {
        axios.get(`http://localhost:8081/api/idm/`, {})
        .then(response => {
          this.cards = response.data;
        })
        .catch(error => {

        })
      },
      createOrg: function() {
        axios.post(`http://localhost:8081/api/org/create`, {
          name: this.formName,
          INN: this.formINN,
          subdivision: this.formSubdiv,
          KPP: this.formKPP,
        })
        .then(response => {
          console.log('succes');
          this.createStatus = false;
        })
        .catch(error => {
          alert(error)
        })
      },
      logoutF: function() {
        this.logout()
        this.$router.push({ name: 'auth' });
      },
      ...mapActions({ logout: 'logout' }),
    },
    created: function() {
      this.getCards();
    },
  }
</script>
