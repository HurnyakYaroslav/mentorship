package com.epam.mentorship.nosql.task2.service;


import com.epam.mentorship.nosql.task2.model.SubTask;
import com.epam.mentorship.nosql.task2.model.Task;
import com.epam.mentorship.nosql.task2.model.TaskCategory;
import com.epam.mentorship.nosql.task2.repository.TaskRepository;
import com.epam.mentorship.nosql.task2.repository.custom.CustomTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubTaskService {

    private final TaskRepository taskRepository;
    private final CustomTaskRepository customTaskRepository;

    public List<SubTask> getAllSubtasksByTaskCategory(TaskCategory taskCategory) {
        return taskRepository.findTasksByCategory(TaskCategory.DEVELOP).stream()
                .flatMap(t -> t.getSubTasks().stream())
                .collect(Collectors.toList());
    }

    public void insertSubTask(String id, SubTask subTask) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found task with id " + id));
        task.getSubTasks().add(subTask);
        taskRepository.save(task);
    }

    public void updateSubTask(String taskId, String subTaskId, SubTask subTask) {
        customTaskRepository.updateSubTask(taskId, subTaskId, subTask);
    }

    public void deleteSubTask(String taskId, String subTaskId) {
        customTaskRepository.deleteSubTask(taskId, subTaskId);
    }

    public List<Task> getSubTasksByWordInDescription(String word) {
        return customTaskRepository.getTasksByWordInSubTaskName(word);
    }

}
