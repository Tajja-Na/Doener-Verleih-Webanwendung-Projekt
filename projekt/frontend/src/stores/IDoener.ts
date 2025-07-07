import type { IZutatDTD } from "./IZutatDTD"

  export interface IDoenerDTD {
    id: number
    bezeichnung: string
    preis: number
    verfuegbarkeit: number;
    vegetarizitaet: number
    zutaten: Array<IZutatDTD>
  }
