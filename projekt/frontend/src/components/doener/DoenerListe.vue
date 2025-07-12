<template>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Bezeichnung</th>
                <th>Preis</th>
                <th>Verfügbarkeit</th>
                <th>Vegetarizitaet</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <DoenerListeZeile v-for="doen in props.doener" :key="doen.id" :doener="doen"/>
        </tbody>
    </table>
</template>

<script setup lang="ts">
    import type { IDoenerDTD } from '@/stores/IDoener';
    import DoenerListeZeile from './DoenerListeZeile.vue';

    const props = defineProps<{
        doener : IDoenerDTD[]
    }>()

</script>