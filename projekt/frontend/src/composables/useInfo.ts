import { readonly, ref} from 'vue'

const info = ref("");

export function useInfo(){

    function loecheInfo(){
        info.value = "";
    }

    function setzeInfo(msg:string){
        info.value = msg;
    }

    return{
        info: readonly(info),
        loecheInfo,
        setzeInfo,
    }
}