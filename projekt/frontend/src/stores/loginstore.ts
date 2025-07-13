import { defineStore } from "pinia";
import { computed, reactive } from "vue";
import { useInfo } from "@/composables/useInfo";

export const useLoginStore = defineStore("loginstore", () => {
    const loginState = reactive<{username:string; loggedIn:boolean; jwt:string}>
                                ({username:"", loggedIn:false, jwt:""})
    const {setzeInfo} = useInfo()

    function logout(){
        loginState.username = ""
        loginState.loggedIn = false
        loginState.jwt = ""
    }

    async function login(username: string, losung:string){
        try{
            const response = await fetch('/api/token',{
                method:"POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({username, losung}),
                redirect: 'error'
            })
            if(!response.ok){
                setzeInfo(response.statusText)
                logout()
                throw new Error(response.statusText)
            }else{

                const jwt = await response.text()

                loginState.username = username
                loginState.loggedIn = true
                loginState.jwt = jwt

                setzeInfo("")
                console.log("Einloggen erfolgreich")
            }
        }catch(error:any){
            logout()
            console.log("Fehler beim einloggen")
        }
    }

    return {
        username :computed(() => loginState.username),
        loggedIn :computed(() => loginState.loggedIn),
        jwt :computed(() => loginState.jwt),
        login,
        logout
    }
})