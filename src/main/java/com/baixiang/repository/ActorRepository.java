package com.baixiang.repository;

import com.baixiang.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findActorByActorName(String actorName);

    List<Actor> getActorByActorNameContaining(String actorName);
}
