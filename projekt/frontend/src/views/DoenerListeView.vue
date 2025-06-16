<template>
  <div class="main">
    <div >
      <div >
        <div>
          <p class="title">Unser aktuelles Dönerangebot</p>
          <p class="heading">Nur wenige Klicks trennen Sie von Ihrem Traumdöner</p>
        </div>
      </div>
    </div>
    <div >
      <img src="@/assets/doener-ritter-tiere.png" />
    </div>
  </div>
  <div class="main">
    <DoenerListe :doener="doenerliste"></DoenerListe>
  </div>
</template>

<style>
  
.main{
  display: flex;
  margin: auto;
  justify-content: center;
}

</style>

<script setup lang="ts">
  import { ref } from 'vue'
  import DoenerListe from '@/components/doener/DoenerListe.vue'

  export interface IDoenerDTD {
    id: number
    bezeichnung: string
    preis: number
    vegetarizitaet: number
    zutaten: Array<IZutatDTD>
  }

  export interface IZutatDTD {
    ean: string
    name: string
    vegetarizitaet: number
  }

  const doenerliste = ref<IDoenerDTD[]>(
    JSON.parse(`
  [
    {
      "id": 2802,
      "bezeichnung": "Bronzongdön",
      "preis": 13,
      "vegetarizitaet": 0,
      "zutaten": [
        {
          "ean": "1101049047855",
          "name": "Eisbergsalat",
          "vegetarizitaet": 2
        },
        {
          "ean": "4474445326792",
          "name": "Fladenbrot",
          "vegetarizitaet": 1
        },
        {
          "ean": "7806470514874",
          "name": "Kalbsschnipsel",
          "vegetarizitaet": 0
        },
        {
          "ean": "3398697207454",
          "name": "Knoblauch",
          "vegetarizitaet": 2
        }
      ]
    },
    {
      "id": 2,
      "bezeichnung": "Fleischdön",
      "preis": 5,
      "vegetarizitaet": 0,
      "zutaten": [
        {
          "ean": "4474445326792",
          "name": "Fladenbrot",
          "vegetarizitaet": 1
        },
        {
          "ean": "8645075438735",
          "name": "Frikadelle",
          "vegetarizitaet": 0
        },
        {
          "ean": "7806470514874",
          "name": "Kalbsschnipsel",
          "vegetarizitaet": 0
        },
        {
          "ean": "8709274658213",
          "name": "Lamm da",
          "vegetarizitaet": 0
        },
        {
          "ean": "7103802900732",
          "name": "Putenschnipsel",
          "vegetarizitaet": 0
        }
      ]
    },
    {
      "id": 1,
      "bezeichnung": "Gesundöner",
      "preis": 10,
      "vegetarizitaet": 2,
      "zutaten": [
        {
          "ean": "1101049047855",
          "name": "Eisbergsalat",
          "vegetarizitaet": 2
        },
        {
          "ean": "1715334440614",
          "name": "Grapefruit",
          "vegetarizitaet": 2
        },
        {
          "ean": "5013842346499",
          "name": "Gurke",
          "vegetarizitaet": 2
        }
      ]
    },
    {
      "id": 2803,
      "bezeichnung": "Wiglettdön",
      "preis": 17,
      "vegetarizitaet": 0,
      "zutaten": [
        {
          "ean": "8645075438735",
          "name": "Frikadelle",
          "vegetarizitaet": 0
        },
        {
          "ean": "1715334440614",
          "name": "Grapefruit",
          "vegetarizitaet": 2
        },
        {
          "ean": "9150715186721",
          "name": "Rösti",
          "vegetarizitaet": 1
        },
        {
          "ean": "7763273447981",
          "name": "Weißkohl",
          "vegetarizitaet": 2
        }
      ]
    }
  ]
  `),
  )
</script>

<style scoped></style>
