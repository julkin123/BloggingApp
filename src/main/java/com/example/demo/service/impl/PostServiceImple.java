package com.example.demo.service.impl;

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

import com.example.demo.entities.Category;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourseNotFoundException;
import com.example.demo.payloads.PostDto;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.PostRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.PostService;

@Service
public class PostServiceImple implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("User", "User id", userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourseNotFoundException("Category", "Category id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);

		Post newPost = postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		this.postRepo.save(post);

		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		this.postRepo.deleteById(postId);

	}

	@Override
	public List<PostDto> getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
		Page<Post> pagePost = postRepo.findAll(p);

		List<Post> post = pagePost.getContent();

		List<PostDto> postDto = post.stream().map((posts) -> this.modelMapper.map(posts, PostDto.class))
				.collect(Collectors.toList());
		return postDto;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "PostId", postId));

		PostDto postDto = this.modelMapper.map(post, PostDto.class);

		return postDto;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourseNotFoundException("Category", "catogaryId", categoryId));

		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Post> posts1 = postRepo.findByCategory(category, p);
		List<Post> posts = posts1.getContent();

		List<PostDto> postdto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postdto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId, Integer pageNumber, Integer pageSize) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("userId", "userIdId", userId));
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Post> posts1 = postRepo.findByUser(user, p);
		List<Post> posts = posts1.getContent();

		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDto;
	}

	@Override
	public List<PostDto> searchPost(String keyWord) {
		List<Post> posts = postRepo.findByTitle("%" + keyWord + "%");
		List<PostDto> post1 = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return post1;
	}

}
