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
      <DoenerListe :doener="liste"></DoenerListe>
    </div>
  </div>
</template>

<style>
</style>

<script setup lang="ts">
  import { storeToRefs } from 'pinia'
  import DoenerListe from '@/components/doener/DoenerListe.vue'
  import { useDoenerStore } from '@/stores/doenerstore';
  import { onMounted, ref, watch } from 'vue';
  import type { IDoenerDTD } from '@/stores/IDoener';

  const doenerStore = useDoenerStore()
  const { updateDoenerListe } = doenerStore
  const { ok, doenerliste } = storeToRefs(doenerStore)

  const liste = ref<IDoenerDTD[]>([])

  const begriff = ref("")

  onMounted( () => {
    updateDoenerListe()  //Beachten Sie bitte, dass die Dönerliste im Store erst gefüllt wird, wenn jemand updateDoenerListe() aufruft
    liste.value = doenerliste.value
  });

  watch(begriff, (neuerBegriff) => {
    if(neuerBegriff == ""){
      liste.value = doenerliste.value
    }else{
      suche(begriff.value)
    }
  })

  function suche(begriff: string){
    liste.value = doenerliste.value.filter(ele => 
      ele.bezeichnung.toLowerCase().includes(begriff.toLowerCase()) || 
      ele.zutaten.some(z => z.name.toLowerCase().includes(begriff.toLowerCase())))
  }
    
</script>