package com.epam.mentorship.nosql.task3;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;

public class LogDao {

    private final CassandraConnector cassandraConnector = new CassandraConnector();
    private final Session session;

    public LogDao() {
        cassandraConnector.connect("127.0.0.2", 9042);
        session = cassandraConnector.getSession();
    }

    public void createKeySpace() {
        StringBuilder sb =
                new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append("db_log").append(" WITH replication = {")
                        .append("'class':'").append("SimpleStrategy")
                        .append("','replication_factor':").append(1)
                        .append("};");

        String query = sb.toString();
        session.execute(query);
    }

    public void createTable() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append("db_log.logs(")
                .append("id int PRIMARY KEY, ")
                .append("message text,")
                .append("level text);");

        String query = sb.toString();
        session.execute(query);
    }

    public long writeLogs(List<Log> logs) {

        List<String> queries = new ArrayList<>();
        for (Log log : logs) {
             queries.add("INSERT INTO " +
                     "db_log.logs" + "(id, message, level) " +
                     "VALUES (" +
                     log.getId() + ", '" +
                     log.getMessage() + "', '" +
                     log.getLevel() +
                     "');");
        }

        long start = System.currentTimeMillis();
        for (String query: queries) {
            session.execute(query);
        }
        return System.currentTimeMillis() - start;
    }

    public long writeLogsBatch(List<Log> logs) {

        StringBuilder sb = new StringBuilder().append("BEGIN BATCH ");
        for (Log log : logs) {
            sb.append("INSERT INTO db_log.logs(id, message, level) VALUES (")
                    .append(log.getId()).append(", '")
                    .append(log.getMessage()).append("', '")
                    .append(log.getLevel()).append("');");
        }
        sb.append("APPLY BATCH;");

        long start = System.currentTimeMillis();
        session.execute(sb.toString());
        return System.currentTimeMillis() - start;
    }

    public Log selectById(int id){
        String query = "SELECT * FROM db_log.logs WHERE id = " + id  + ";";
        ResultSet rs = session.execute(query);

        Log.LogBuilder log = Log.builder();

        Row row = rs.one();
        if(row == null) {
            return null;
        }

        log.id(row.getInt("id"))
                .message(row.getString("message"))
                .level(row.getString("level"));

        return log.build();
    }

    public void updateLogById(int id) {
        String query = " UPDATE db_log.logs SET level='error' WHERE id = " + id + ";";

        session.execute(query);
    }

    public void deleteLogById(int id) {
        String query = " DELETE FROM db_log.logs WHERE id = " + id + ";";

        session.execute(query);
    }

    public void close() {
        cassandraConnector.close();
    }

}
