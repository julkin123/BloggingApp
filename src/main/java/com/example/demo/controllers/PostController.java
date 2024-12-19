package com.example.demo.controllers;

import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
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

import com.example.demo.config.AppConstants;
import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.PostDto;
import com.example.demo.service.FileService;
import com.example.demo.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;

	// create Post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto postDto1 = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(postDto1, HttpStatus.CREATED);

	}

	// get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getpostByUser(@PathVariable Integer userId,
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {
		List<PostDto> posts = postService.getPostByUser(userId, pageNumber, pageSize);

		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	// get post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getpostCategory(@PathVariable Integer categoryId,
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {
		List<PostDto> posts = postService.getPostByCategory(categoryId, pageNumber, pageSize);

		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	// get post by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto postDto = this.postService.getPostById(postId);

		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);

	}

	// get all post
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost(
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy) {

		List<PostDto> postDto = this.postService.getAllPost(pageNumber, pageSize, sortBy);
		return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);

	}

	// delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);

		return new ApiResponse("Post is deleted", true);
	}

	// update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto postDto1 = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(postDto1, HttpStatus.OK);

	}

	// search
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> search(@PathVariable String keyword) {
		List<PostDto> posts = postService.searchPost(keyword);

		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}
	// upload image in post

	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam MultipartFile image, @PathVariable Integer postId)
			throws IOException {

		String fileName = this.fileService.uploadImage(path, image);
		PostDto postDto = this.postService.getPostById(postId);
		postDto.setImageName(fileName);
		PostDto updatepost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatepost, HttpStatus.OK);

	}

	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}

}
