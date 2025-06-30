<template>
  <div class="main-main">
    <div class="intro">
      <div class="text">
        <p class="title">Unser aktuelles Dönerangebot</p>
        <p>Nur wenige Klicks trennen Sie von Ihrem Traumdöner</p>
      </div>
      <div class="image">
        <img src="@/assets/doener-ritter-tiere.png" />
      </div>
    </div>

    <div class="suche">
      <input type="text" v-model="begriff">
      <button @click="begriff = ''"> Reset </button>
    </div>

    <div class="main">
      <DoenerListe :doener="gefiltereteListe"></DoenerListe>
    </div>
  </div>
</template>

<style>
</style>

<script setup lang="ts">
  import { storeToRefs } from 'pinia'
  import DoenerListe from '@/components/doener/DoenerListe.vue'
  import { useDoenerStore } from '@/stores/doenerstore';
  import { computed, onMounted, ref, watch } from 'vue';

  const doenerStore = useDoenerStore()
  const { updateDoenerListe, startDoenerLiveUpdate } = doenerStore
  const { ok, doenerliste } = storeToRefs(doenerStore)

  const begriff = ref("")

  onMounted( () => {
      updateDoenerListe()
      startDoenerLiveUpdate();
  });

 const gefiltereteListe = computed(() => {
  if(begriff.value === ""){
    return doenerliste.value
  }

  return doenerliste.value.filter(ele => 
    ele.bezeichnung.toLowerCase().includes(begriff.value.toLowerCase()) || 
    ele.zutaten.some(z => z.name.toLowerCase().includes(begriff.value.toLowerCase()))
  )
})
    
</script>