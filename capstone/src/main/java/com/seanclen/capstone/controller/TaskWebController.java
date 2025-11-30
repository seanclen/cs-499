package com.seanclen.capstone.controller;

import com.seanclen.capstone.model.Task;
import com.seanclen.capstone.service.TaskService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
public class TaskWebController {

    private final TaskService taskService;

    public TaskWebController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());

        return "tasks"; 
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("task", new Task());

        return "tasks_form";
    }

    @GetMapping("/{id}") // Maps to GET /tasks/{id} for editing
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Task task = taskService.getTaskById(id);
            
            // Pass the Task entity directly to the model
            model.addAttribute("task", task); 
            return "tasks_form";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Task not found: " + e.getMessage());
            return "redirect:/tasks";
        }
    }
    
    @PostMapping 
    public String createTask(@ModelAttribute Task task,
                                RedirectAttributes redirectAttributes) {
        
        try {
            taskService.createTask(task.getName(), task.getDescription());
            redirectAttributes.addFlashAttribute("message", "Task created successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to create task: " + e.getMessage());
        }

        return "redirect:/tasks";
    }

    @PutMapping("/{id}")
    public String updateTask(@PathVariable String id,
                                @ModelAttribute Task task,
                                RedirectAttributes redirectAttributes) {
        try {
            taskService.updateTask(id, task.getName(), task.getDescription());
            redirectAttributes.addFlashAttribute("message", "Task updated successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update task: " + e.getMessage());
        }
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            taskService.deleteTask(id);
            redirectAttributes.addFlashAttribute("message", "Task deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete task: " + e.getMessage());
        }
        return "redirect:/tasks";
    }
}