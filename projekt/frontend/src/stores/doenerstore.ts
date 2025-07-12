import { defineStore } from "pinia";
import { computed, onMounted, reactive } from "vue";
import type { IDoenerDTD } from './IDoener'
import { useInfo } from "@/composables/useInfo";
import { Client } from "@stomp/stompjs";
import { useLoginStore } from "./loginstore";
import type { IFrontendNachrichtEvent } from "@/services/IFrontendNachrichtEvent";
import { storeToRefs } from 'pinia'

export const useDoenerStore = defineStore("doenerstore", () => {
    const doenerdata = reactive<{ok:boolean; doenerliste:IDoenerDTD[]; entlieheneDoener:IDoenerDTD[]}>
                                ({ok:false, doenerliste:[], entlieheneDoener:[]})

    const { info, loecheInfo, setzeInfo} = useInfo()
    const loginStore = useLoginStore()
    const { username } = storeToRefs(loginStore)

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

    async function updateEntlieheneDoenerListe(loginName:string){
        try{
            const response = await fetch('/api/'+loginName)
            if(!response.ok){
                setzeInfo(response.statusText)
                throw new Error(response.statusText)
            }else{
                const data = await response.json()
                console.log('update entliehene doener liste aufgerufen')
                doenerdata.entlieheneDoener = data;
            }
        }catch(error:any){
            doenerdata.entlieheneDoener = [];
            setzeInfo("Liste konnte nicht geladen werden")
        }
    }

    async function leihen(doenerId:number, loginName:string){
        try{
            const response = await fetch('/api/entleihung',{
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({doenerId, loginName})
            });
            if(!response.ok){
                setzeInfo(response.statusText)
                throw new Error(response.statusText)
            }else{
                console.log("Leihen erfolgreich")
            }
        }catch(error:any){
            setzeInfo("Nicht genug Döner vorhanden")
        }
    }

    async function zurueckgeben(loginName:string, doenerId:number){
        try{
            const response = await fetch('/api/'+loginName+'/' +doenerId,{
                method: 'DELETE'
            });
            if(!response.ok){
                setzeInfo(response.statusText)
                throw new Error(response.statusText)
            }else{
                console.log("Zurüchgeben erfolgreich")
            }
        }catch(error:any){
            setzeInfo("Zurüchgeben nicht erfolgreich gewesen")
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
            stompclient?.subscribe(DEST, async (message) => {
                try{
                    console.log("erfolg: ", JSON.stringify(frame))
                    const eventObj: IFrontendNachrichtEvent = JSON.parse(message.body);
                    console.log("Empfangenes FrontendNachrichtEvent: ", JSON.stringify(eventObj))

                    if(eventObj.typ === "DOENER"){
                        await updateDoenerListe();
                        await updateEntlieheneDoenerListe(username.value);
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
        entlieheneDoener :computed(() => doenerdata.entlieheneDoener),
        updateDoenerListe,
        startDoenerLiveUpdate,
        updateEntlieheneDoenerListe,
        leihen,
        zurueckgeben
    }
})