package com.codewithyad.blog.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithyad.blog.entities.Catagory;
import com.codewithyad.blog.entities.Post;
import com.codewithyad.blog.entities.User;

public interface PostRepositories extends JpaRepository<Post,Integer> {
	List<Post> findByUser(User user);
	List<Post> findByCatagory(Catagory catagory);
	List<Post> findByTitleContaining(String title);
}
