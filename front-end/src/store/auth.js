import Vue from 'vue';
import Vuex from 'vuex';
import axios from '../axios'

import {sha256} from '../utils/hash';

Vue.use(Vuex);

export const state ={
    username: '',
    jwtAuthToken: '',
};

export const mutations ={
    LOGIN_SUCCESS(state, { jwtAuthToken, username }){
        state.jwtAuthToken = jwtAuthToken;
        state.username = decodeURI(username);
    },
    TOKEN_UPDATE_SUCCESS(state, {jwtAuthToken, username}){
        state.jwtAuthToken = jwtAuthToken;
        state.username = decodeURI(username);
    },
    LOGOUT(state){
        state.username = null;
        state.jwtAuthToken = null;
    }
};

export const getters = {
    encodedUsername: (state) =>{
        return encodeURI(state.username);
    },
    nickname: (state) =>{
        return state.username;
    },
    jwtAuthToken: (state)=>{
        return state.jwtAuthToken;
    },
};

export const actions ={
    login({ commit }, { uid, password }){
        return new Promise((resolve, reject)=>{
            commit('LOGOUT');

            axios({
                method:'post',
                url:'/user/login',
                data:{
                    uid,
                    password,
                }
            })
                .then((response)=>{
                    if(200 <= response.status && response.status < 300){
                        const jwtAuthToken = response.headers['jwt-auth-token'];
                        const username = response.headers['username'];


                        commit('LOGIN_SUCCESS', {jwtAuthToken, username});
                        
                        resolve(response);
                    }else{
                        reject(response);
                    }
                })
                .catch(error =>{
                    reject(error);
                });
        });
    },
    logout({ commit }){
        return new Promise((resolve) =>{
            commit('LOGOUT');

            resolve();
        });
    },
    register({ uid, password, nickname, username, auth_date, gender, age }){
        return new Promise((resolve, reject) =>{
            axios({
                method: 'post',
                url: '/user/signup',
                data: {
                    uid,
                    password,
                    nickname,
                    username,
                    auth_date,
                    gender,
                    age
                },
            })
                .then((response)=>{
                    if(200 <= response.status && response.status < 300){
                        resolve(response);
                    }else{
                        reject(response);
                    }
                })
                .catch(error=>{
                    reject(error);
                });
        }) 
    },
    fetchUser({ username }) {
        return new Promise((resolve, reject) => {
            axios({
                method: 'get',
                url: '/user/detail',
                params: {
                    username,
                }
            })
                .then((response) => {
                    if (200 <= response.status && response.status < 300) {
                        resolve(response);
                    } else {
                        reject(response);
                    }
                })
                .catch((error) => {
                    reject(error);
                });
        });
    },
    deleteUser({ commit }, { username }) {
        return new Promise((resolve, reject) => {
            axios({
                method: 'delete',
                url: '/user',
                params: {
                    username,
                }
            })
                .then((response) => {
                    if (200 <= response.status && response.status < 300) {
                        commit('LOGOUT');

                        resolve(response);

                        commit('LOGOUT');
                    } else {
                        reject(response);
                    }
                })
                .catch((error) => {
                    reject(error);
                });
        });
    },
    updateUser({ commit }, formData) {
        formData.set('password', sha256(formData.get('password')));
        return new Promise((resolve, reject) => {
            axios({
                method: 'put',
                url: '/user',
                data: formData,
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
                .then((response) => {
                    if (200 <= response.status && response.status < 300) {
                        const jwtAuthToken = response.headers['jwt-auth-token'];
                        const username = response.headers['username'];
                        const userpk = response.headers['userpk']
    
                        commit('TOKEN_UPDATE_SUCCESS', { jwtAuthToken, username, userpk });

                        resolve(response);
                    } else {
                        reject(response);
                    }
                })
                .catch((error) => {
                    reject(error);
                });
        });
    },
    extendJWT({commit}) {
        return new Promise((resolve, reject) => {
            axios({
                method: 'get',
                url: '/user/extendJWT',
            })
                .then((response) => {
                    if (200 <= response.status && response.status < 300) {
                        const jwtAuthToken = response.headers['jwt-auth-token'];
                        const username = response.headers['username'];
    
                        commit('TOKEN_UPDATE_SUCCESS', { jwtAuthToken, username });

                        resolve(response);
                    } else {
                        commit('LOGOUT');
                        
                        reject(response);
                    }
                })
                .catch((error) => {
                    commit('LOGOUT');

                    reject(error);
                });
        });
    },

};
export const modules = {

}