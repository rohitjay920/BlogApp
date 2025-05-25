package com.blog.service.implementation;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blog.dto.CategoryDto;
import com.blog.dto.ResponseStructure;
import com.blog.entity.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repository.CategoryRepository;
import com.blog.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CategoryServiceImplementation implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public ResponseEntity<ResponseStructure<CategoryDto>> createCategory(CategoryDto categoryDto) {
		Category category = objectMapper.convertValue(categoryDto, Category.class);
		ResponseStructure<CategoryDto> rs = new ResponseStructure<>();
		rs.setData(objectMapper.convertValue(categoryRepository.save(category),CategoryDto.class));
		rs.setMessage("Category created");
		rs.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<CategoryDto>>(rs,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<CategoryDto>> updateCategory(CategoryDto categoryDto, int id) {
		Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category with id: "+id+" not found"));
		category = objectMapper.convertValue(categoryDto, Category.class);
		category.setId(id);
		Category updatedCategory = categoryRepository.save(category);
		ResponseStructure<CategoryDto> rs = new ResponseStructure<>();
		rs.setData(objectMapper.convertValue(updatedCategory, CategoryDto.class));
		rs.setMessage("Category updated");
		rs.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(rs);
	}

	@Override
	public ResponseEntity<ResponseStructure<CategoryDto>> getCategory(int id) {
		Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category with id: "+id+" not found"));
		ResponseStructure<CategoryDto> rs = new ResponseStructure<>();
		rs.setData(objectMapper.convertValue(category, CategoryDto.class));
		rs.setMessage("Category found");
		return ResponseEntity.ok(rs);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<CategoryDto>>> getAllCategory() {
		List<CategoryDto> list = categoryRepository.findAll().stream().map(obj -> objectMapper.convertValue(obj, CategoryDto.class)).collect(Collectors.toList());
		ResponseStructure<List<CategoryDto>> rs = new ResponseStructure<>();
		rs.setData(list);
		rs.setMessage("Categories found");
		rs.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(rs);
	}

	@Override
	public ResponseEntity<String> deleteCategory(int id) {
		Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category with id: "+id+" not found"));
		categoryRepository.delete(category);
		return new ResponseEntity<String>("Category deleted",HttpStatus.OK);
	}

}
