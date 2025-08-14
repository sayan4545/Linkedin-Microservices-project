package com.sayan.linkedin.PostService.repositories;

import com.sayan.linkedin.PostService.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
