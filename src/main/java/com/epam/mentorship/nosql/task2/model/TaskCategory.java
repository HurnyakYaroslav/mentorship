package com.epam.mentorship.nosql.task2.model;

public enum TaskCategory {
  DEVELOP("develop"), FIX("fix");

  private String name;

  TaskCategory(String name) {
    this.name = name;
  }
}
