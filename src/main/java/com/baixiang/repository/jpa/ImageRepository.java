package com.baixiang.repository.jpa;

import com.baixiang.model.jpa.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    public Image findById(long id);
}
