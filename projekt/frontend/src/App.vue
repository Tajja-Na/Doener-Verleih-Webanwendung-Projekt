<template>
  <header>
      <h1> Dönerverleih UdE</h1>
      <h1> Katalog </h1>
  </header>

  <div v-if="info" class="info-box">
    <div id="a1">
      <span> Info </span>
      <button @click="info = ''"> X </button>
    </div>

    <div id="a2">
      <span>{{ info }}</span>
    </div>
  </div>
  <DoenerListeView/>
  <footer>
  </footer>
</template>

<style src="./assets/main.css"></style>

<script setup lang="ts">
import { ref } from "vue"
import DoenerListeView from "./views/DoenerListeView.vue";

const info = ref("Fitness-Deal: Für jeden veganen Döner gibt es zwei unvegetarische gratis!")

</script>