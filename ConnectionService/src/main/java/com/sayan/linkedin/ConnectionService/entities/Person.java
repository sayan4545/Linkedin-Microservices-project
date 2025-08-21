package com.sayan.linkedin.ConnectionService.entities;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Data
public class Person {
    @Id
    @Generated
    private Long id;

    private Long userId;
    private String name;
}
