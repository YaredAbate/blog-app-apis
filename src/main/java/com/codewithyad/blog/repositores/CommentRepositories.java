package com.codewithyad.blog.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithyad.blog.entities.Comment;

public interface CommentRepositories extends JpaRepository<Comment,Integer>{

}
