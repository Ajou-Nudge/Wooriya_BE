package com.nudge.wooriya.adapter.out.persistence.MongoEntity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
@Getter
@Setter
public class Landing {
    String cafeName;
    String phoneNumber;
    String cafeAdress;
    String instaId;
    String email;
}
