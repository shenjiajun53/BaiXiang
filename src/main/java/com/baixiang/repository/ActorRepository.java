package com.baixiang.repository;

import com.baixiang.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findActorByActorName(String actorName);
}
