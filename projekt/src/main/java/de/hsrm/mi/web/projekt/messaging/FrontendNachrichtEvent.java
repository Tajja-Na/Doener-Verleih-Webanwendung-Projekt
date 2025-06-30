package de.hsrm.mi.web.projekt.messaging;

public class FrontendNachrichtEvent {
    private NachrichtenTyp typ;
    private long id;
    private Operation operation;

    public FrontendNachrichtEvent(NachrichtenTyp typ, long id, Operation operation){
        this.id = id;
        this.typ = typ;
        this.operation = operation;
    }

    public NachrichtenTyp getTyp() {
        return typ;
    }
    public void setTyp(NachrichtenTyp typ) {
        this.typ = typ;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Operation getOperation() {
        return operation;
    }
    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
