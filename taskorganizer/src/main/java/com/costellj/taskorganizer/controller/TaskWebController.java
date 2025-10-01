package com.costellj.taskorganizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.costellj.taskorganizer.model.Task;
import com.costellj.taskorganizer.repository.TaskRepository;

@Controller
@RequestMapping("/tasks")
public class TaskWebController {

    private final TaskRepository taskRepository;

    public TaskWebController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Show Add Task Form
    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

    // Save New Task
    @PostMapping("/web")
    public String saveTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    // List Tasks
    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "task-list";
    }

    // Show Edit Form
    @GetMapping("/edit")
    public String showEditForm(@RequestParam Long id, Model model) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id: " + id));
        model.addAttribute("task", task);
        return "task-form";
    }

    // Update Task (used for editing title or toggling completed)
    @PostMapping("/update")
    public String updateTask(@RequestParam Long id,
                             @RequestParam String title,
                             @RequestParam boolean completed) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id: " + id));
        task.setTitle(title);
        task.setCompleted(completed);
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    // Delete Task
    @GetMapping("/delete")
    public String deleteTask(@RequestParam Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }
}
