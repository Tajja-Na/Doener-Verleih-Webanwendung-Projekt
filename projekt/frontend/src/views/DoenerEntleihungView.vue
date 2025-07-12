<template>
  <div class="main-main">
    <div class="intro">
      <div class="text">
        <p class="title">Ihre Entleihungen</p>
        <p>Diese Spezialitäten haben Sie zur Zeit entliehen</p>
      </div>
      <div class="image">
        <img src="@/assets/doener-ritter-tiere.png" />
      </div>
    </div>

    <div class="main">
      <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Bezeichnung</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="doener in entlieheneDoener">
                <td>{{ doener.id }}</td>
                <td>{{ doener.bezeichnung }}</td>
                <td><button @click="zurueckgeben(username, doener.id)">Rückgabe</button></td>
            </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<style>
</style>

<script setup lang="ts">
    import { storeToRefs } from 'pinia'
    import { useDoenerStore } from '@/stores/doenerstore';
    import { computed, onMounted, ref, watch } from 'vue';
    import { useLoginStore } from '@/stores/loginstore';
    
    const loginStore = useLoginStore()
    const doenerStore = useDoenerStore()
    const { updateDoenerListe, startDoenerLiveUpdate, updateEntlieheneDoenerListe, zurueckgeben } = doenerStore
    const { entlieheneDoener } = storeToRefs(doenerStore)
    const { username } = storeToRefs(loginStore)

    const begriff = ref("")

    onMounted( () => {
        updateDoenerListe()
        startDoenerLiveUpdate();
        updateEntlieheneDoenerListe(username.value);
    });
    
</script>