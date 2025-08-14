package com.sayan.linkedin.PostService.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long userId;
}
