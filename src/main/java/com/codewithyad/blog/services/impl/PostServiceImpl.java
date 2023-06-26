package com.codewithyad.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithyad.blog.entities.Catagory;
import com.codewithyad.blog.entities.Post;
import com.codewithyad.blog.entities.User;
import com.codewithyad.blog.execeptions.ResourceNotFoundExeception;
import com.codewithyad.blog.payloads.PostDto;
import com.codewithyad.blog.payloads.PostResponse;
import com.codewithyad.blog.repositores.CatagoryRepositories;
import com.codewithyad.blog.repositores.PostRepositories;
import com.codewithyad.blog.repositores.UserRepositories;
import com.codewithyad.blog.services.PostService;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepositories postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepositories userRepo;
	@Autowired
	private CatagoryRepositories catagoryRepo;
	public PostDto createPost(PostDto postDto,Integer userId,Integer catagoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->
		new ResourceNotFoundExeception("user","Id",userId));
		Catagory catagory=this.catagoryRepo.findById(catagoryId).orElseThrow(()->
		new ResourceNotFoundExeception("catagory","Id",catagoryId));
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCatagory(catagory);
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepo.findById(postId).
				orElseThrow(()->new ResourceNotFoundExeception("post","Id",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost=this.postRepo.save(post);
		PostDto postDto1=this.modelMapper.map(updatedPost,PostDto.class);
		return postDto1;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).
				orElseThrow(()->new ResourceNotFoundExeception("post","Id",postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable p=PageRequest.of(pageNumber,pageSize,sort);
		Page<Post> pagePost=this.postRepo.findAll(p);
		List<Post> allPosts=pagePost.getContent();
		List<PostDto> postDtos=allPosts.stream().map((post)->
		this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).
				orElseThrow(()->new ResourceNotFoundExeception("post","Id",postId));
		return this.modelMapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCatagory(Integer catagoryId) {
		Catagory cat=this.catagoryRepo.findById(catagoryId).orElseThrow(()->
		new ResourceNotFoundExeception("catagory","Id",catagoryId));
		List<Post> posts=this.postRepo.findByCatagory(cat);
		List<PostDto> postDto=posts.stream().map((post)->
		this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->
		new ResourceNotFoundExeception("user","Id",userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDto=posts.stream().map((post)->
		this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts=this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos=posts.stream().map((post)
				->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
