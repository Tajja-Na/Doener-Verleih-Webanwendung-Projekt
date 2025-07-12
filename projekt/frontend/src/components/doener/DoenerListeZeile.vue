<template>
        <tr>
            <td>{{ props.doener.id }}</td>
            <td>{{ props.doener.bezeichnung }}</td>
            <td>{{ props.doener.preis }}</td>
            <td>{{ props.doener.verfuegbar }}</td>
            <td>{{ vegetarizitaet }}</td>
            <td class="buttons">
                <button @click="zeigeZutaten = !zeigeZutaten">?</button>
                <button @click="leihen(props.doener.id, username)">leihen</button>
            </td>
        </tr>
        <tr v-if="zeigeZutaten" v-for="zutat in doener.zutaten" :key="zutat.ean">
            <td class="zutaten"><img :src="`/images/zutaten/${zutat.ean}.png`"/></td>
            <td class="zutaten" colspan="2">
                <a :href="`https://de.wikipedia.org/wiki/${zutat.name}`" target="_blank">
                    {{ zutat.name }}
                </a>
            </td>
            <td class="zutaten" colspan="2">{{ berechneVegWert(zutat.vegetarizitaet) }}</td>
        </tr>
</template>

<script setup lang="ts">
    import type { IDoenerDTD } from "@/stores/IDoener";
    import {ref} from "vue"
    import { useDoenerStore } from "@/stores/doenerstore";
    import { useLoginStore } from '@/stores/loginstore';
    
    const {username} = useLoginStore()

    const {leihen} = useDoenerStore()

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
    const zeigeZutaten = ref(false)
</script>

<style>
    .zutaten{
        border-bottom: 0px;
    }
    .buttons{
        display: flex;
        gap: 0.8rem;
    } 
</style>