package com.codewithyad.blog.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithyad.blog.config.AppConstant;
import com.codewithyad.blog.payloads.ApiResponse;
import com.codewithyad.blog.payloads.PostDto;
import com.codewithyad.blog.payloads.PostResponse;
import com.codewithyad.blog.services.FileService;
import com.codewithyad.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	@PostMapping("/user/{userId}/catagory/{catagoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto,@PathVariable Integer userId,@PathVariable Integer catagoryId){
		PostDto createPost=this.postService.createPost(postdto, userId, catagoryId);
		return new ResponseEntity<>(createPost,HttpStatus.CREATED);
	}
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> posts=this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	@GetMapping("/catagory/{catagoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCatagory(@PathVariable Integer catagoryId){
		List<PostDto> posts=this.postService.getPostByCatagory(catagoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value="pageNumber",
	defaultValue=AppConstant.PAGE_NUMBER,required=false)Integer pageNumber,@RequestParam(value="pageSize",
	defaultValue=AppConstant.PAGE_SIZE,required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstant.SORTBY,required=false)String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstant.SORTDIR,required=false)String sortDir){
		PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer postId){
		PostDto post=this.postService.getPostById(postId);
		return ResponseEntity.ok(this.postService.getPostById(postId));
	}
	@DeleteMapping("/post/{userId}")
	 public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
		 this.postService.deletePost(uid);
		 return new ResponseEntity(new ApiResponse("post deleted succesfully",true),
				 HttpStatus.OK);
	 }
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody 
			PostDto postDto,@PathVariable Integer postId){
		PostDto updatePost=this.postService.updatePost(postDto, postId);
		return ResponseEntity.ok(updatePost);
	}
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> findByTitleContaining(@PathVariable("keyword")
	String keyword){
		List<PostDto> postDto=this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);
	}
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId) throws IOException{
		String fileName=this.fileService.uploadImage(path, image);
		PostDto postDto=this.postService.getPostById(postId);
		postDto.setImageName(fileName);
		PostDto updatedPost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	@GetMapping(value="/post/image/imageName/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName")
	String imageName,HttpServletResponse response) throws IOException {
		InputStream resource=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	}
}
