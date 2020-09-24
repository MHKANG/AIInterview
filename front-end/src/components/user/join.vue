<template>
  <div>
    <div>
      <h1>회원가입</h1>
      <table>
        <tr>
          <td class="td">이메일</td>
          <td class="td">
            <input v-model="email" id="email" placeholder="이메일을 입력해주세요" type="text" class="input" />
          </td>
        </tr>
        <tr>
          <td class="td">비밀번호</td>
          <td class="td">
            <input
              v-model="password"
              id="name"
              placeholder="비밀번호를 입력해주세요"
              type="text"
              class="input"
            />
          </td>
        </tr>
        <tr>
          <td class="td">닉네임</td>
          <td class="td">
            <input
              v-model="nickname"
              id="nickname"
              placeholder="닉네임을 입력해주세요"
              type="text"
              class="input"
            />
          </td>
        </tr>
        <tr>
          <td class="td">이름</td>
          <td class="td">
            <input
              v-model="username"
              id="username"
              placeholder="유저이름을 입력해주세요"
              type="text"
              class="input"
            />
          </td>
        </tr>
        <tr>
          <td class="td">성별</td>
          <td class="td">
            <v-select v-model="el" :items="gender"></v-select>
          </td>
        </tr>
        <tr>
          <td class="td">나이</td>
          <td class="td">
            <input v-model="age" id="age" placeholder="나이를 입력해주세요" type="number" class="input" />
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
// var storage = window.localStorage;

import constants from "../../constants";
export default {
  data() {
    return {
      el: "성별",
      gender: ["남성", "여성"],
      email: "",
      password: "",
      nickname: "",
      username: "",
      age: "",
    };
  },
  methods: {
    signIn() {
      if (this.email == "") {
        alert("이메일을 입력해주세요");
        return;
      }
      if (this.password == "") {
        alert("비밀번호를 입력해주세요");
        return;
      }
      if (this.nickname == "") {
        alert("닉네임을 입력해주세요");
        return;
      }
      if (this.username == "") {
        alert("이름을 입력해주세요");
        return;
      }
      if (this.gender == "성별") {
        alert("성별을 입력해주세요");
        return;
      }
      if (this.age == "") {
        alert("나이를 입력해주세요");
      } else {
        let temail = this.email;
        let tpassword = this.password;
        let tnickname = this.nickname;
        let tusername = this.username;
        let tgender = this.gender;
        let tage = this.age;

        this.$store.dispatch(constants.SINGIN, {
          temail,
          tpassword,
          tnickname,
          tusername,
          tgender,
          tage,
        });
        this.goToPages;
      }
    },
    goToPages() {
      this.$router.push({
        name: constants.URL_TYPE.POST.MAIN,
      });
    },
  },
};
</script>

<style scoped>
.td {
  padding: 20px;
}
</style>