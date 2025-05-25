package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.CategoryDto;
import com.blog.dto.ResponseStructure;
import com.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<CategoryDto>> createCategory(@Valid @RequestBody CategoryDto category){
		return categoryService.createCategory(category);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<CategoryDto>> updateCategory(@Valid @RequestBody CategoryDto category, @PathVariable int id){
		return categoryService.updateCategory(category, id);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<CategoryDto>> getCategory(@PathVariable int id){
		return categoryService.getCategory(id);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<CategoryDto>>> getAllCategory(){
		return categoryService.getAllCategory();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable int id){
		return categoryService.deleteCategory(id);
	}
}
