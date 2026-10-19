package com.wzr26.onlineexam.repository;

import com.wzr26.onlineexam.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
