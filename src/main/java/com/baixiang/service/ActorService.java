package com.baixiang.service;

import com.baixiang.model.Actor;
import com.baixiang.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public Actor getActorByName(String actorName) {
        return actorRepository.findActorByActorName(actorName);
    }
}
