import { defineStore } from "pinia";
import { computed, onMounted, reactive } from "vue";
import type { IDoenerDTD } from './IDoener'
import { useInfo } from "@/composables/useInfo";
import { Client } from "@stomp/stompjs";
import type { IFrontendNachrichtEvent } from "@/services/IFrontendNachrichtEvent";

export const useDoenerStore = defineStore("doenerstore", () => {
    const doenerdata = reactive<{ok:boolean; doenerliste:IDoenerDTD[]}>
                                ({ok:false, doenerliste:[]})

    const { info, loecheInfo, setzeInfo} = useInfo()
    const wsurl = `ws://${window.location.host}/stompbroker`;
    const DEST = "/topic/doener";
    let stompclient: Client | null = null;

    async function updateDoenerListe(){
        try{
            const response = await fetch('/api/doener')
            if(!response.ok){
                setzeInfo(response.statusText)
                throw new Error(response.statusText)
            }else{
                const data = await response.json()
                doenerdata.doenerliste = data;
                doenerdata.ok = true;
            }
        }catch (error:any){
            doenerdata.doenerliste = [];
            doenerdata.ok = false;
        }
    }

    function startDoenerLiveUpdate(){
        if(stompclient && stompclient.connected){
            return;
        }

        if(stompclient == null){
            stompclient = new Client({brokerURL:wsurl})
            console.log("hab client erstellt")
        }   

        stompclient.onWebSocketError = (event) => { 
            console.log("fehler: ", JSON.stringify(event))
        }

        stompclient.onStompError = (event) => { 
            console.log("fehler: ", JSON.stringify(event))
        }

        stompclient.onConnect = (frame) => {
            stompclient?.subscribe(DEST, (message) => {
                try{
                    console.log("erfolg: ", JSON.stringify(frame))
                    const eventObj: IFrontendNachrichtEvent = JSON.parse(message.body);
                    console.log("Empfangenes FrontendNachrichtEvent: ", JSON.stringify(eventObj))

                    if(eventObj.typ === "DOENER"){
                        updateDoenerListe();
                        console.log("update")
                    }
                }catch(e){
                    setzeInfo("Fehler beim Verarbeiten der Nachricht: " + e)
                }
            })
        }

        stompclient.activate();
        
    }

    return {
        ok :computed(() => doenerdata.ok),
        doenerliste :computed(() => doenerdata.doenerliste),
        updateDoenerListe,
        startDoenerLiveUpdate
    }
})