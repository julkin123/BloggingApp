package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.exceptions.ResourseNotFoundException;
import com.example.demo.payloads.CommentDto;
import com.example.demo.repository.CommentRepo;
import com.example.demo.repository.PostRepo;
import com.example.demo.service.CommentService;
import org.modelmapper.ModelMapper;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	PostRepo postRepo;
	@Autowired
	CommentRepo commentRepo;
	@Autowired
	ModelMapper modelmapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundException("post", "postId", postId));
		Comment comment = this.modelmapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment comment1 = this.commentRepo.save(comment);
		CommentDto commentDto1 = this.modelmapper.map(comment1, CommentDto.class);

		return commentDto1;
	}

	@Override
	public void deleteComent(Integer commentId) {

		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourseNotFoundException("comment", "comment id", commentId));
		commentRepo.deleteById(commentId);

	}

}
