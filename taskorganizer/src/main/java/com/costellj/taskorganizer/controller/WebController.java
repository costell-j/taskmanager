package com.costellj.taskorganizer.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.costellj.taskorganizer.repository.TaskRepository;

public class WebController {
    private final TaskRepository taskRepository;

    public WebController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "index";
    }
}
