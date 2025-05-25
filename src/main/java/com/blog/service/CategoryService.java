package com.blog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.blog.dto.CategoryDto;
import com.blog.dto.ResponseStructure;

public interface CategoryService {
	
	ResponseEntity<ResponseStructure<CategoryDto>> createCategory(CategoryDto categoryDto);
	
	ResponseEntity<ResponseStructure<CategoryDto>> updateCategory(CategoryDto categoryDto, int id);
	
	ResponseEntity<ResponseStructure<CategoryDto>> getCategory(int id);
	
	ResponseEntity<ResponseStructure<List<CategoryDto>>> getAllCategory();
	
	ResponseEntity<String> deleteCategory(int id);
}
