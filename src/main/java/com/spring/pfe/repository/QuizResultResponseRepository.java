package com.spring.pfe.repository;

import com.spring.pfe.models.Quiz;
import com.spring.pfe.models.QuizResultResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultResponseRepository extends JpaRepository<QuizResultResponse, Long> {
}
