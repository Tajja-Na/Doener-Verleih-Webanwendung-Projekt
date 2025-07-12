<template>
  <header>
      <h1> Dönerverleih UdE</h1>
      <div class="links">
          <RouterLink to="/doener" v-if="loggedIn"> Katalog </RouterLink>
          <RouterLink to="/entleihung"> Deine Döner </RouterLink>
          <RouterLink to="/login" @click= logout> Login </RouterLink>
      </div>
  </header>

  <div v-if="info" class="info-box">
    <div id="a1">
      <span> Info </span>
      <button @click= loecheInfo> X </button>
    </div>

    <div id="a2">
      <span>{{ info }}</span>
    </div>
  </div>
  <RouterView/>
  <footer>
    <div v-if="loggedIn">
      <p>{{ username }}</p>
    </div>
  </footer>
</template>

<style src="./assets/main.css"></style>

<script setup lang="ts">
  import { useInfo } from '@/composables/useInfo'
  import { RouterView } from 'vue-router';
  import { storeToRefs } from 'pinia';
  import { useLoginStore } from './stores/loginstore';
  import { onMounted, watch } from 'vue';
  import { useRouter } from 'vue-router';

  const { info, loecheInfo, setzeInfo} = useInfo()

  const { logout } = useLoginStore()
  const loginStore = useLoginStore()
  const {username, loggedIn } = storeToRefs(loginStore)

  const router = useRouter()

  onMounted(() => {
    if(router.currentRoute.value.path === '/doener'){
      setzeInfo("Willkommen, Döner-Community!")
    }else if(router.currentRoute.value.path === '/login'){
      loecheInfo()
    }
  })

  watch(() => router.currentRoute.value.path, (neuerPfad) =>{
    if(neuerPfad === '/doener'){
      setzeInfo("Willkommen, Döner-Community!")
    }else{
      loecheInfo()
    }
  })

</script>