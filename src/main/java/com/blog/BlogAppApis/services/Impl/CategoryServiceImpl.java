package com.blog.BlogAppApis.services.Impl;

import com.blog.BlogAppApis.entities.Category;
import com.blog.BlogAppApis.exceptions.ResourceNotFoundException;
import com.blog.BlogAppApis.payloads.CategoryDto;
import com.blog.BlogAppApis.respositories.CategoryRepo;
import com.blog.BlogAppApis.services.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(@Valid CategoryDto categoryDto) {
        Category addedCat=this.categoryRepo.save(this.modelMapper.map(categoryDto, Category.class));
        return this.modelMapper.map(addedCat,CategoryDto.class);

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category cat=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(
                "Category","CategoryId",id
        ));

        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        this.categoryRepo.save(cat);
        return this.modelMapper.map(cat,CategoryDto.class);

    }

    @Override
    public void deleteCategory(Integer id) {
        Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(
                "Category","CategoryId",id
        ));
        this.categoryRepo.delete(category);

    }

    @Override
    public CategoryDto getCategory(Integer id) {
        Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(
                "Category","CategoryId",id
        ));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> cats=this.categoryRepo.findAll();
        return cats.stream().map(cat->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
    }


}
