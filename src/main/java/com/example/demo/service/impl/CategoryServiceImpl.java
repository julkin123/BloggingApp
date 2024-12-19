package com.example.demo.service.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.exceptions.ResourseNotFoundException;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.dtoToEntity(categoryDto);
		categoryRepo.save(category);
		return entityToDto(category);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourseNotFoundException("Category", "CategoryId", categoryId));
		category = dtoToEntity(categoryDto);
		categoryRepo.save(category);

		return categoryDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub

		Category catogary = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourseNotFoundException("Catogary", "CatogaryId", categoryId));
		categoryRepo.delete(catogary);

	}

	@Override
	public List<CategoryDto> getAllCategory() {

		return categoryRepo.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourseNotFoundException("Category", "catogaryId", categoryId));
		CategoryDto catagoryDto = entityToDto(category);
		return catagoryDto;
	}

	public CategoryDto entityToDto(Category category) {
		CategoryDto catogaryDto = new CategoryDto();

		catogaryDto.setCategoryId(category.getCategoryId());
		catogaryDto.setCategoryTitle(category.getCategoryTitle());
		catogaryDto.setCategoryDiscription(category.getCategoryDiscription());

		return catogaryDto;

	}

	public Category dtoToEntity(CategoryDto categoryDto) {
		Category category = new Category();

		category.setCategoryId(categoryDto.getCategoryId());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDiscription(categoryDto.getCategoryDiscription());

		return category;

	}

}
