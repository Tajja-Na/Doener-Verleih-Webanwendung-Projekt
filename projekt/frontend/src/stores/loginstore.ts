import { defineStore } from "pinia";
import { computed, reactive } from "vue";

export const useLoginStore = defineStore("loginstore", () => {
    const loginState = reactive<{username:string; loggedIn:boolean}>
                                ({username:"", loggedIn:false})

    function logout(){
        loginState.username = ""
        loginState.loggedIn = false
    }

    function login(username: string, losung:string){
        if(username.trim() != "" && losung.trim() != ""){
            loginState.username = username
            loginState.loggedIn = true
        }else{
            logout()
        }
    }

    return {
        username :computed(() => loginState.username),
        loggedIn :computed(() => loginState.loggedIn),
        login,
        logout
    }
})