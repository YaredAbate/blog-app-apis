package com.codewithyad.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithyad.blog.entities.Comment;
import com.codewithyad.blog.entities.Post;
import com.codewithyad.blog.execeptions.ResourceNotFoundExeception;
import com.codewithyad.blog.payloads.CommentDto;
import com.codewithyad.blog.repositores.CommentRepositories;
import com.codewithyad.blog.repositores.PostRepositories;
import com.codewithyad.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private PostRepositories postRepo;
	@Autowired
	private CommentRepositories commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post=this.postRepo.findById(postId).
				orElseThrow(()->new ResourceNotFoundExeception("post","Id",postId));
		Comment comment=this.modelMapper.map(commentDto,Comment.class);
		comment.setPost(post);
		Comment SavedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(SavedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com=this.commentRepo.findById(commentId).
				orElseThrow(()->new ResourceNotFoundExeception("comment","Id",commentId));
		this.commentRepo.delete(com);
	}

}
