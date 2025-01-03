package com.futurevault.communicationservice;

import com.futurevault.entites.Capsules;
import com.futurevault.repositories.CapsulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;
import java.util.List;

public class CommunicationServiceImp {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CapsulesRepository capsulesRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void sendEmail() {

        List<Capsules> capsules = capsulesRepository.findAll();

        for (Capsules capsule : capsules){
            if ((!capsule.getMessageSent())&&(capsule.getUnlockdatetime().equals(LocalDateTime.now())||capsule.getUnlockdatetime().isBefore(LocalDateTime.now()))){
                emailService.sendEmail(capsule.getTargetemail(), capsule.getMessage(), capsule.getTitle());
                 capsule.setMessageSent(true);
                 capsulesRepository.save(capsule);
            }
        }

    }
}
