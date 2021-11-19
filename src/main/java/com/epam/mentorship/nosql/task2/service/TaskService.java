package com.epam.mentorship.nosql.task2.service;

import com.epam.mentorship.nosql.task2.model.Task;
import com.epam.mentorship.nosql.task2.model.TaskCategory;
import com.epam.mentorship.nosql.task2.repository.TaskRepository;
import com.epam.mentorship.nosql.task2.repository.custom.CustomTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final CustomTaskRepository customTaskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTaskByTaskCategory(TaskCategory taskCategory) {
        return taskRepository.findTasksByCategory(taskCategory);
    }

    public void insertTask(Task task) {
        taskRepository.save(task);
    }

    public void updateTask(String id, Task task) {
        task.setId(id);
        taskRepository.save(task);
    }

    public void delete(String id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getOverdueTasks() {
        return taskRepository.getOverdueTasks(LocalDateTime.now());
    }

    public List<Task> getTasksByWordInDescription(String word) {
        return customTaskRepository.getTasksByWordInDescription(word);
    }

    public List<Task> getTasksWithTheSpecificCategory(TaskCategory category) {
        return taskRepository.findTasksByCategory(category);
    }

}
