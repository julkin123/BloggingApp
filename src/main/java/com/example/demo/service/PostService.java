package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Category;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.payloads.PostDto;

public interface PostService {
	// create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// update
	PostDto updatePost(PostDto postDto, Integer postId);

	// delete
	void deletePost(Integer postId);
	// get all post

	List<PostDto> getAllPost(Integer pageNumber, Integer pageSize, String sortBy);

	// get post by id
	PostDto getPostById(Integer postId);

	// get post by category

	List<PostDto> getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);
	// get post by User

	List<PostDto> getPostByUser(Integer userId, Integer pageNumber, Integer pageSize);

	// search by title
	List<PostDto> searchPost(String keyWord);

}
