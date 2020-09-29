<template>
  <div>
    <div>
      <h1>회원가입</h1>
      <table>
        <tr>
          <td class="td">이메일</td>
          <td class="td">
            <input
              v-model="email"
              id="email"
              placeholder="이메일을 입력해주세요"
              type="text"
              class="input"
            />
          </td>
          <td class="td">
            <v-btn text color="blue darken-3" @click="emailCheck"
              >중복확인</v-btn
            >
          </td>
        </tr>
        <tr>
          <td class="td">비밀번호</td>
          <td class="td">
            <input
              v-model="password"
              id="name"
              placeholder="비밀번호를 입력해주세요"
              type="password"
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
            <input
              v-model="age"
              id="age"
              placeholder="나이를 입력해주세요"
              type="number"
              class="input"
            />
          </td>
        </tr>
      </table>

      <v-col cols="5">
        <v-btn text color="blue darken-3" @click="signIn">작성완료</v-btn>
      </v-col>
    </div>
  </div>
</template>

<script>
// var storage = window.localStorage;

import Constants from "../../Constants";
import axios from "axios";
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
        axios({
          method: "post",
          url: "http://localhost:8081/api/user",
          data: {
            uid: this.email,
            password: this.password,
            nickname: this.nickname,
            username: this.username,
            is_admin: false,
            is_activated: true,
            auth_date: null,
            gender: this.el,
            age: this.age,
          },
        })
          .then((data) => {
            console.dir(data);
            let msg = "회원 가입 중 문제가 발생";
            if (data.data == "success") {
              msg = "회원 가입 완료";
            }
            alert(msg);
          })
          .catch((error) => {
            console.log(error);
          });
      }
      this.$router.push("/");
    },

    emailCheck() {
      var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      axios({
        method: "GET",
        url: "http://localhost:8081/api/user/emailCheck?email=" + this.email,
      })
        .then((data) => {
          let msg = "이미 가입된 이메일 입니다.";
          console.log(data);
          if (this.email == "") {
            alert("이메일을 입력해 주세요.");
            return;
          }
          if (this.email.match(regExp) == null) {
            alert("이메일 형식이 맞지 않습니다.");
            return;
          }
          if (data.data == false) {
            msg = "사용 가능한 이메일 입니다.";
          }
          alert(msg);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    goToPages() {
      this.$router.push({
        name: Constants.URL_TYPE.POST.MAIN,
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
