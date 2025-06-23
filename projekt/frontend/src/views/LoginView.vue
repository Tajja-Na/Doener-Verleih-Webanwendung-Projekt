<template>
    <h1> Login </h1>
    <div v-if="info" class="info-box">
        <div id="a1">
            <span> Info </span>
            <button @click= loecheInfo> X </button>
        </div>´
        <div id="a2">
            <span>{{ info }}</span>
        </div>
    </div>
    
    <input type="text" v-model="usernameEingabe">
    <input type="password" v-model="losung">
    <button @click="einloggen(usernameEingabe, losung)"> Login! </button>
</template>

<style>
</style>

<script setup lang="ts">
    import { onMounted, ref } from 'vue';
    import { useInfo } from '@/composables/useInfo';
    import { useLoginStore } from '@/stores/loginstore';
    import { storeToRefs } from 'pinia';

    const { info, loecheInfo, setzeInfo} = useInfo()
    setzeInfo("Fehler!")

    const usernameEingabe = ref("")
    const losung = ref("")

    const loginStore = useLoginStore()
    const { login, logout } = loginStore
    const { loggedIn } = storeToRefs(loginStore)

    onMounted( () => {
        logout()
    });

    function einloggen(usernameEingabe:string, losungEingabe:string){
        login(usernameEingabe, losungEingabe)

        if(!loggedIn.value){
            losung.value = ""
            setzeInfo("Jammer, der Login-Versuch war nicht erfolgreich")
        }else{
            setzeInfo("")
        }
    }

</script>