package com.example.demo.service;

import java.util.List;

import com.example.demo.payloads.CategoryDto;

public interface CategoryService {

	// Create
	CategoryDto createCategory(CategoryDto categoryDto);

	// update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// delete
	void deleteCategory(Integer categoryId);

	// get all
	List<CategoryDto> getAllCategory();

	// get by Id
	CategoryDto getCategoryById(Integer categoryId);

}
