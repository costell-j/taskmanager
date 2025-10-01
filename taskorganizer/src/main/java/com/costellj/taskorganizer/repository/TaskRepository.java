package com.costellj.taskorganizer.repository;

import org.springframework.stereotype.Repository;

import com.costellj.taskorganizer.model.Task;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
