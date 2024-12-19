package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.CategoryDto;
import com.example.demo.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto) {

		CategoryDto categoryDto1 = categoryService.createCategory(categoryDto);

		return new ResponseEntity<CategoryDto>(categoryDto1, HttpStatus.CREATED);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCatogary(@RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId) {

		CategoryDto cate = categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(cate, HttpStatus.OK);

	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) {

		categoryService.deleteCategory(categoryId);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {

		return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {

		return new ResponseEntity<CategoryDto>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
	}

}
