package com.epam.mentorship.nosql.task3;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Log {

    private int id;
    private String message;
    private String level;

}

