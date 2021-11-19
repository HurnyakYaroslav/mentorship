package com.epam.mentorship.nosql.task2.repository;


import com.epam.mentorship.nosql.task2.model.SubTask;
import com.epam.mentorship.nosql.task2.model.Task;
import com.epam.mentorship.nosql.task2.model.TaskCategory;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
  List<Task> findTasksByCategory(TaskCategory category);

  @Aggregation(" {$unwind:'$subTasks'},\n" +
          " {$project:{name: '$subTasks.name', description: '$subTasks.description', id: '$subTasks._id'}}) ")
  List<SubTask> findSubTaskByTaskCategory();

  @Query("{'deadlineDate' : {$lt: ?0}}")
  List<Task> getOverdueTasks(LocalDateTime nowDate);

}
