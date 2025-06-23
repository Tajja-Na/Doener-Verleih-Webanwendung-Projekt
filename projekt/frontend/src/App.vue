<template>
  <header>
      <h1> Dönerverleih UdE</h1>
      <h1> Katalog </h1>
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
  <DoenerListeView/>
  <footer>
    <div v-if="loggedIn">
      <p>{{ username }}</p>
    </div>
  </footer>
</template>

<style src="./assets/main.css"></style>

<script setup lang="ts">
  import { useInfo } from '@/composables/useInfo'
  import DoenerListeView from "./views/DoenerListeView.vue";
  import { storeToRefs } from 'pinia';
  import { useLoginStore } from './stores/loginstore';

  const { info, loecheInfo, setzeInfo} = useInfo()
  setzeInfo("Willkommen, Döner-Community!")

  const loginStore = useLoginStore()
  const {username, loggedIn } = storeToRefs(loginStore)

</script>