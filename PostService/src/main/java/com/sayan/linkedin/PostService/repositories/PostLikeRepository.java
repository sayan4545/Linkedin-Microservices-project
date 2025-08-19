package com.sayan.linkedin.PostService.repositories;

import com.sayan.linkedin.PostService.entities.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
    boolean existsByUserIdandPostId(Long userId, Long postId);

    void deleteByUserIdAndPostId(Long userId, Long postId);
}
