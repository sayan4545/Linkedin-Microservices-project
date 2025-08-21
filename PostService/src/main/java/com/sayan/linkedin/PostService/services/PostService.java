package com.sayan.linkedin.PostService.services;

import com.sayan.linkedin.PostService.auth.AuthContextHolder;
import com.sayan.linkedin.PostService.dtos.PostCreationRequestDTO;
import com.sayan.linkedin.PostService.dtos.PostDTO;
import com.sayan.linkedin.PostService.entities.Post;
import com.sayan.linkedin.PostService.exceptions.ResourceNotFoundException;
import com.sayan.linkedin.PostService.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDTO createPost(PostCreationRequestDTO postCreationRequestDTO,Long userId){
        log.info("Creating post ");
        Post post = modelMapper.map(postCreationRequestDTO,Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDTO.class);
    }

    public PostDTO getPostById(Long postId){
        log.info("retrieving post by a particular postId : {}",postId);
        Long userId = AuthContextHolder.getCurrentUserId();
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found with id : "+postId));
        return modelMapper.map(post, PostDTO.class);
    }

    public List<PostDTO> getAllPostByUser(Long userId){
        log.info("retrieving all posts by user with userId : {}",userId);
        return postRepository.findAllByUserId(userId)
                .stream()
                .map(post -> modelMapper.map(post,PostDTO.class))
                .toList();
    }
}
