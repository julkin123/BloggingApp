package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Category;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
	Page<Post> findByUser(User user, Pageable p);

	Page<Post> findByCategory(Category category, Pageable p);

	@Query("select p from Post p where p.title like:key")
	List<Post> findByTitle(@Param("key") String keyword);

}
