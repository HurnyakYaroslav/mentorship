package com.epam.mentorship.nosql.task1;

import com.mongodb.*;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MigrationJob {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/mentorship?allowMultiQueries=true";
    static final String USER = "postgres";
    static final String PASS = "root";

    public void migrate() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("university");
        MongoCollection collection = database.getCollection("user");


        try(ClientSession clientSession = mongoClient.startSession();
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement()) {
            clientSession.startTransaction();
            migrateStudentData(stmt, collection);
            migrateExamData(stmt, collection, conn);
            clientSession.commitTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void migrateStudentData(Statement stmt, MongoCollection collection) throws SQLException {
        ResultSet rsStudent = stmt.executeQuery(
                "SELECT students.id, students.name, students.surname, students.phone_number\n" +
                        "FROM students\n");
        while (rsStudent.next()) {
            Document document = new Document();
            document
                    .append("_id", "s_" + rsStudent.getInt("id"))
                    .append("name", rsStudent.getString("name"))
                    .append("surname", rsStudent.getString("surname"))
                    .append("phone_number", rsStudent.getString("phone_number"));

            collection.insertOne(document);
        }
        rsStudent.close();
    }

    private void migrateExamData(
            Statement stmt, MongoCollection collection, Connection connection) throws SQLException {
        Statement subjectStmt = connection.createStatement();
        ResultSet rsExams = stmt.executeQuery("SELECT * FROM exam_result");
        while (rsExams.next()) {
            Document document = new Document();
            document
                    .append("_id", "t_" + rsExams.getInt("id"))
                    .append("mark", rsExams.getString("mark"));

            ResultSet rsSubject =
                    subjectStmt.executeQuery("SELECT students.id as student_id, students.name as student_name" +
                            " FROM exam_result JOIN students ON exam_result.student_id = students.id WHERE students.id = " +
                            rsExams.getInt("student_id"));
            List<Document> students = new ArrayList<>();
            while (rsSubject.next()) {
                log.info("new Student {}", rsSubject.getString("student_id"));
                students.add(new Document()
                        .append("id", rsSubject.getString("id"))
                        .append("name", rsSubject.getString("student_name")));
            }
            rsSubject.close();

            document.append("student", students);
            collection.insertOne(document);
        }
        rsExams.close();
    }
}
