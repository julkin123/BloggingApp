package com.example.demo.service;

import com.example.demo.payloads.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComent(Integer commentId);

}
