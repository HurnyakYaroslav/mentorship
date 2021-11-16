package com.epam.mentorship.nosql.task1.repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Aggregates.match;


@Slf4j
public class UserRepository {

    private final MongoClient mongoClient;
    private final MongoDatabase database;

    public UserRepository() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("school");
    }

    public List<Document> getStudents() {
        MongoCollection<Document> collection = database.getCollection("user");

        return collection.aggregate(Collections.singletonList(
                match(Filters.regex("_id", "s_\\d+")))).into(new ArrayList<>());
    }

    public List<Document> getStudentsByClassName(String className) {
        MongoCollection<Document> collection = database.getCollection("user");
        collection.createIndex(Indexes.text("class_name"));

        return collection.aggregate(Collections.singletonList(
                match(Filters.text("7-А")))).into(new ArrayList<>());
    }

}
