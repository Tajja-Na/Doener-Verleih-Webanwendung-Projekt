<template>
    <div class="login">
        <h1> Login </h1>
        <div class="input-felder">
            <input type="text" v-model="usernameEingabe">
            <input type="password" v-model="losung">
        </div>
        <button @click="einloggen(usernameEingabe, losung)"> Login! </button>
    </div>
</template>

<style>
</style>

<script setup lang="ts">
    import { onMounted, ref } from 'vue';
    import { useInfo } from '@/composables/useInfo';
    import { useLoginStore } from '@/stores/loginstore';
    import { storeToRefs } from 'pinia';
    import { useRouter } from 'vue-router';

    const { info, loecheInfo, setzeInfo} = useInfo()

    const usernameEingabe = ref("")
    const losung = ref("")

    const loginStore = useLoginStore()
    const { login } = loginStore
    const { loggedIn } = storeToRefs(loginStore)
    
    const router = useRouter()

    async function einloggen(usernameEingabe:string, losungEingabe:string){
        await login(usernameEingabe, losungEingabe)

        if(!loggedIn.value){
            losung.value = ""
            setzeInfo("Jammer, der Login-Versuch war nicht erfolgreich")
        }else{
            router.push("/doener")
            setzeInfo("")
        }
    }

</script>