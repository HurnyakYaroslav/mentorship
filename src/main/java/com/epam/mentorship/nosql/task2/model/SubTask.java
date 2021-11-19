package com.epam.mentorship.nosql.task2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.TextIndexed;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubTask {
  private String id;
  @TextIndexed
  private String name;
  private String description;
}
