<template>
  <v-app-bar
    v-scroll="onScroll"
    :color="!isScrolling ? 'transparent' : 'white'"
    fixed
    flat
  >
    <v-slide-x-transition>
      <v-img
        v-if="showLogo"
        :src="require('@/assets/images/ProjectLogo.png')"
        class="shrink"
        contain
        height="50"
      />
    </v-slide-x-transition>

    <v-spacer />

<!--    {{nickname}}님-->
    <v-btn
        dense
        dark
        color="secondary"
        small
        style="margin-left:5px;"
        @click="logout"
    >
      로그아웃
    </v-btn>
  </v-app-bar>
</template>

<script>
// import axios from "@/utils/api";

  export default {
    name: 'CoreAppBar',

    data: () => ({
      showLogo: false,
      isScrolling: false,
    }),

    // created() {
    //   axios.post('/user', {
    //     nickname: this.nickname,
    //   })
    //       .then(response => {
    //         console.log(response);
    //       })
    // },

    methods: {
      onScroll () {
        const offset = window.pageYOffset
        this.isScrolling = offset > 50
        this.showLogo = offset > 200
      },
      logout(){
        this.$store.dispatch('logout');
        // this.$session.destroy();

        alert("로그아웃 됬습니다.")
        this.$router.push("/");
      },
    },
  }
</script>
