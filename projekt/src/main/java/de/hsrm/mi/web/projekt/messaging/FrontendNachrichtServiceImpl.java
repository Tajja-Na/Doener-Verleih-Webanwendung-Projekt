package de.hsrm.mi.web.projekt.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class FrontendNachrichtServiceImpl implements FrontendNachrichtService{
        
    @Autowired
    private SimpMessagingTemplate messaging;
    private final String MY_DEST = "/topic/doener";

    private final Logger logger = LoggerFactory.getLogger(FrontendNachrichtService.class);

    public FrontendNachrichtServiceImpl(SimpMessagingTemplate messaging){
        this.messaging = messaging;
    }

    @Override
    @TransactionalEventListener(phase=TransactionPhase.AFTER_COMMIT)
    public void sendEvent(FrontendNachrichtEvent ev){
        messaging.convertAndSend(MY_DEST, ev);
        logger.info("FrontendnachrichtEvent gesendet an {}: {}", MY_DEST, ev);
    }
}
