package com.epam.mentorship.nosql.task2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("tasks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
  @Id
  private String id;
  private String name;
  @TextIndexed
  private String description;
  private LocalDateTime createdDate;
  private LocalDateTime deadlineDate;
  private TaskCategory category;
  private List<SubTask> subTasks;
}
