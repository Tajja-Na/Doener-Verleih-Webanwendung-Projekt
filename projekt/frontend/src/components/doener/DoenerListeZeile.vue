<template>
        <tr>
            <td>{{ props.doener.id }}</td>
            <td>{{ props.doener.bezeichnung }}</td>
            <td>{{ props.doener.preis }}</td>
            <td>{{ vegetarizitaet }}</td>
            <td><button>?</button></td>
        </tr>
</template>

<script setup lang="ts">
    import type {IDoenerDTD} from '@/views/DoenerListeView.vue';
    import {ref} from "vue"

    const props = defineProps<{
        doener: IDoenerDTD
    }>()

    function berechneVegWert(wert : number ): string{
        switch (wert){
            case 0:
                return "unvegetarisch"
            case 1:
                return "vegetarisch"
            case 2:
                return "vegan"
            default:
                return "nicht bekannt"
        }
    }
    
    const vegetarizitaet = ref(berechneVegWert(props.doener.vegetarizitaet))
</script>