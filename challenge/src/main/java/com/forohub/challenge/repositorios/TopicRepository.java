package com.forohub.challenge.repositorios;

import com.forohub.challenge.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
