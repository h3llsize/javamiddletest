<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700;800;900&display=swap');

html {
  font-family: "Poppins", sans-serif ;

  scroll-behavior: smooth;
}

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  text-decoration: none;
}

ul,
li {
  margin: 0;
  padding: 0;

  list-style: none;
}

button {
  background: none;
  border: none;
  cursor: pointer;
}

input {
  background: none;
  border: none;
  outline: none;
}

img {
  max-width: 100%;
}

h1, h2, h3, h4, h5, h6, p, span, strong {
  margin: 0;
  padding: 0;
}
</style>

<script>
  import { mapActions } from 'vuex';

  export default {
    methods: {
      ...mapActions({ validateToken: 'validateToken' })
    },
    created: function() {
      this.validateToken();
    }
  }
</script>
