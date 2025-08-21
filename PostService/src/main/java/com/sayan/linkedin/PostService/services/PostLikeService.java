package com.sayan.linkedin.PostService.services;

import com.sayan.linkedin.PostService.entities.Post;
import com.sayan.linkedin.PostService.entities.PostLike;
import com.sayan.linkedin.PostService.exceptions.BadCredentialsExceptions;
import com.sayan.linkedin.PostService.exceptions.ResourceNotFoundException;
import com.sayan.linkedin.PostService.repositories.PostLikeRepository;
import com.sayan.linkedin.PostService.repositories.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    @Transactional
    public void likePost(Long postId) {
        // TODO: Extract userId from authentication context instead of hardcoding
        Long userId = 1L; // Temporary hardcoded value, should be replaced with actual user ID from auth
        log.info("Getting the post from the userId {} and postId {}", userId, postId);
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("no post exist by this is : "+ postId));

        boolean isAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(isAlreadyLiked) throw new BadCredentialsExceptions("Already liked");
        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);

        postLikeRepository.save(postLike);

        // todo : send notification the owner about someone liking your post
    }

    @Transactional
    public void unlikePost(Long postId) {
        // TODO: Extract userId from authentication context instead of hardcoding
        Long userId = 1L; // Temporary hardcoded value, should be replaced with actual user ID from auth
        log.info("Getting the post from the userId {} and postId {}", userId, postId);
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("no post exist by this is : "+ postId));

        boolean isAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(!isAlreadyLiked) throw new BadCredentialsExceptions("Cannot unlike a post that is not liked");
        postLikeRepository.deleteByUserIdAndPostId(userId,postId);


    }
}
