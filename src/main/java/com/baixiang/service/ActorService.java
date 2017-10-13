package com.baixiang.service;

import com.baixiang.model.jpa.Actor;
import com.baixiang.repository.jpa.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public Actor getActorUnique(String actorName) {
        Actor actor = getActorByName(actorName);
        if (actor == null) {
            actor = new Actor();
            actor.setActorName(actorName);
            actor = save(actor);
        }
        return actor;
    }

    public Actor getActorByName(String actorName) {
        return actorRepository.findActorByActorName(actorName);
    }

    public Actor save(Actor actor) {
        return actorRepository.saveAndFlush(actor);
    }

    public List<Actor> getActorIncludeName(String actorName) {
        return actorRepository.getActorByActorNameContaining(actorName);
    }
}
