package com.example.store.controllers;

import com.example.store.dtos.ProductDto;
import com.example.store.dtos.RegisterUserRequest;
import com.example.store.dtos.UserDto;
import com.example.store.entities.Category;
import com.example.store.entities.Product;
import com.example.store.entities.User;
import com.example.store.mappers.ProductMapper;
import com.example.store.repositories.CategoryRepository;
import com.example.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public Iterable<ProductDto> getAllProducts(@RequestParam(required = false, name = "categoryId") Byte id){
        List<Product> productList;
        if(id != null){
            productList = productRepository.findByCategoryId(id);
        }
        else {
            productList = productRepository.findAllWithCategory();
        }
        return productList.stream().map(productMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){
        Product prod = productRepository.findById(id).orElse(null);
        if(prod == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productMapper.toDto(prod));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto,
                                                    UriComponentsBuilder uriComponentsBuilder){

        var category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(null);

        if(category == null){
            return ResponseEntity.badRequest().build();
        }

        var product = productMapper.toEntity(productDto);

        productRepository.save(product);
        productDto.setId(product.getId());

        var uri = uriComponentsBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }


    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
       var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
       if(category == null) return ResponseEntity.badRequest().build();

       var product = productRepository.findById(id).orElse(null);
       if(product == null){
           return ResponseEntity.notFound().build();
       }

       productMapper.update(productDto, product);
       productRepository.save(product);
       productDto.setId(product.getId());
       return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        var product = productRepository.findById(id).orElse(null);

        if(product == null) return ResponseEntity.notFound().build();

        productRepository.delete(product);

        return ResponseEntity.noContent().build();
    }

}
