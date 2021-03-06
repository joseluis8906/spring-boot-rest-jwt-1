package com.jose.samples.api.cuenta;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public void add(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @GetMapping
    public List<Task> list() {
        return taskRepository.findAll();
    }

    @PutMapping("/{id}")
    public void edit(@PathVariable long id, @RequestBody Task task) {
        Task existingTask = taskRepository.findOne(id);
        Assert.notNull(existingTask, "Task not found");
        existingTask.setDescription(task.getDescription());
        taskRepository.save(existingTask);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        taskRepository.delete(id);
    }
}
