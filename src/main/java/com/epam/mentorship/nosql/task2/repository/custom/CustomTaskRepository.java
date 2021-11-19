package com.epam.mentorship.nosql.task2.repository.custom;

import com.epam.mentorship.nosql.task2.model.SubTask;
import com.epam.mentorship.nosql.task2.model.Task;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomTaskRepository {

  private final MongoTemplate mongoTemplate;
  private final String COLLECTION_NAME = "tasks";

  public List<Task> getTasksByWordInDescription(String world) {
    TextIndexDefinition textIndex = new TextIndexDefinition.TextIndexDefinitionBuilder()
        .onField("description")
        .build();
    mongoTemplate.indexOps(Task.class).ensureIndex(textIndex);
    TextCriteria criteria = new TextCriteria().matchingAny(world);
    Query query = TextQuery.queryText(criteria).sortByScore();
    return mongoTemplate.find(query, Task.class, COLLECTION_NAME);
  }

  public List<Task> getTasksByWordInSubTaskName(String world) {
    TextIndexDefinition textIndex = new TextIndexDefinition.TextIndexDefinitionBuilder()
        .onField("subTasks.name")
        .build();
    mongoTemplate.indexOps(COLLECTION_NAME).ensureIndex(textIndex);

    TextCriteria criteria = new TextCriteria().matchingAny(world);
    Query query = TextQuery.queryText(criteria).sortByScore();
    return mongoTemplate.find(query, Task.class, COLLECTION_NAME);
  }

  public void updateSubTask(String taskId, String subtaskId, SubTask subTask) {
    Query query = Query.query(Criteria.where("_id").is(taskId)
            .and("subTasks._id").is(subtaskId));
    Update update = new Update();
    update.set("subTasks.$", subTask);
    mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
  }

  public void deleteSubTask(String taskId, String subtaskId) {
    Query query = Query.query(Criteria.where("_id").is(taskId)
            .and("subTasks._id").is(subtaskId));
    Update update = new Update();
    update.pull("subTasks", new Document("_id", subtaskId));
    mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
  }
}
