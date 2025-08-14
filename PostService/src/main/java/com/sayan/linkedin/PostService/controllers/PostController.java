package com.sayan.linkedin.PostService.controllers;

import com.sayan.linkedin.PostService.dtos.PostCreationRequestDTO;
import com.sayan.linkedin.PostService.dtos.PostDTO;
import com.sayan.linkedin.PostService.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostCreationRequestDTO postCreationRequestDTO, HttpServletRequest request){
        PostDTO postDTO = postService.createPost(postCreationRequestDTO,1L);
        return new ResponseEntity<>(postDTO, HttpStatus.CREATED);

    }


}
