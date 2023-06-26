package com.codewithyad.blog.services;

import java.util.List;

import com.codewithyad.blog.entities.Post;
import com.codewithyad.blog.payloads.PostDto;
import com.codewithyad.blog.payloads.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto,Integer userId,Integer catagoryId);
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	PostDto getPostById(Integer postId);
	List<PostDto> getPostByCatagory(Integer catagoryId);
	List<PostDto> getPostByUser(Integer userId);
	List<PostDto> searchPost(String keyword);
}
