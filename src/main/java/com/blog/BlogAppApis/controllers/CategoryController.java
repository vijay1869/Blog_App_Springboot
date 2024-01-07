package com.blog.BlogAppApis.controllers;

import com.blog.BlogAppApis.payloads.ApiResponse;
import com.blog.BlogAppApis.payloads.CategoryDto;
import com.blog.BlogAppApis.payloads.UserDto;
import com.blog.BlogAppApis.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    //crud
    @Autowired
    private CategoryService categoryService;
    //post-create user
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createDto=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createDto, HttpStatus.CREATED);
    }
    //put-update user
    @PutMapping("/{cId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("cId") Integer cid){
        CategoryDto updatedcategoryDto=this.categoryService.updateCategory(categoryDto,cid);
        return ResponseEntity.ok(updatedcategoryDto);//why do we need to reemove new for ok
    }
    //delete -delete user
    @DeleteMapping("/{cId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer cId){//? if as we dont know type?Any type?
        this.categoryService.deleteCategory(cId);
        return new ResponseEntity<>(new ApiResponse("User deleted Succesfully",true),HttpStatus.OK);
    }
    //get-user get
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getAllCategory());
    }
    @GetMapping("/{cid}")//URI variable(I-Identifier)
    public ResponseEntity<CategoryDto> getSCategory(@PathVariable Integer cid){
        return ResponseEntity.ok(this.categoryService.getCategory(cid));
    }
}
