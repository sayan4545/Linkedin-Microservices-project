package com.sayan.linkedin.PostService.services;

import com.sayan.linkedin.PostService.dtos.PostCreationRequestDTO;
import com.sayan.linkedin.PostService.dtos.PostDTO;
import com.sayan.linkedin.PostService.entities.Post;
import com.sayan.linkedin.PostService.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDTO createPost(PostCreationRequestDTO postCreationRequestDTO,Long userId){
        Post post = modelMapper.map(postCreationRequestDTO,Post.class);
        post.setId(userId);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDTO.class);
    }
}
