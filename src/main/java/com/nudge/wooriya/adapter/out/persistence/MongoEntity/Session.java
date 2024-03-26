package com.nudge.wooriya.adapter.out.persistence.MongoEntity;


import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class Session {
    @Id
    private String id;
    private List<String> members;
}
