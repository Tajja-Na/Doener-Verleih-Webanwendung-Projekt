export interface IFrontendNachrichtEvent {
    typ: "DOENER" | string;
    id: number;
    operation: "CREATE" | "UPDATE" | "DELETE" | string;
}