<template>
    <table>
        <thead>
            <tr>
                <th>id</th>
                <th>bezeichnung</th>
                <th>preis</th>
                <th>vegetarizitaet</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <DoenerListeZeile v-for="doen in props.doener" :key="doen.id" :doener="doen"/>
        </tbody>
    </table>
</template>

<script setup lang="ts">
    import DoenerListeZeile from './DoenerListeZeile.vue';
    import type { IDoenerDTD } from '@/views/DoenerListeView.vue';

    const props = defineProps<{
        doener : IDoenerDTD[]
    }>()

</script>