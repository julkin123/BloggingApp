package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.CommentDto;
import com.example.demo.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId) {

		CommentDto commentDto = this.commentService.createComment(comment, postId);

		return new ResponseEntity<CommentDto>(commentDto, HttpStatus.CREATED);

	}

	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {

		this.commentService.deleteComent(commentId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted", true), HttpStatus.OK);

	}

}
