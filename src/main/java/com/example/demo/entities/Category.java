package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	@Column(name = "title")
	private String categoryTitle;
	@Column(name = "description")
	private String categoryDiscription;
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Post> post = new ArrayList<>();

	public Category(int categoryId, String categoryTitle, String categoryDiscription, List<Post> post) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDiscription = categoryDiscription;
		this.post = post;
	}

	public Category() {
		// TODO Auto-generated constructor stub
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

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDiscription="
				+ categoryDiscription + ", post=" + post + "]";
	}

}
