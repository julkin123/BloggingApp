package com.example.demo.payloads;

import jakarta.validation.constraints.NotEmpty;

public class CategoryDto {

	private int categoryId;
	@NotEmpty
	private String categoryTitle;
	@NotEmpty
	private String categoryDiscription;

	public CategoryDto(int categoryId, String categoryTitle, String categoryDiscription) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDiscription = categoryDiscription;
	}

	public CategoryDto() {

	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDiscription() {
		return categoryDiscription;
	}

	public void setCategoryDiscription(String categoryDiscription) {
		this.categoryDiscription = categoryDiscription;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDiscription="
				+ categoryDiscription + "]";
	}

}
