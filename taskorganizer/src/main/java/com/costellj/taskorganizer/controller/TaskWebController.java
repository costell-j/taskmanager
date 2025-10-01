package com.costellj.taskorganizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.costellj.taskorganizer.model.Task;
import com.costellj.taskorganizer.repository.TaskRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class TaskWebController {
    private final TaskRepository taskRepository;

    public TaskWebController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @PostMapping("/tasks/web")
    public String saveTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "task-list";
    }

    @GetMapping("/tasks/edit")
    public String showEditForm(@RequestParam Long id, Model model) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id: " + id));
        model.addAttribute("task", task);
        return "task-form";
    }

    @PostMapping("/tasks/update")
    public String updateTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirected:/tasks";
    }

    @GetMapping("/tasks/delete")
    public String deleteTask(@RequestParam Long id) {
        taskRepository.deleteById(id);
        return "redirected:/tasks";
    }
    
}

