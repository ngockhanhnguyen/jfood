package com.kadajko.product.resource;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kadajko.product.domain.model.Category;
import com.kadajko.product.domain.model.Product;
import com.kadajko.product.domain.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getAll() {
        return service.getAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category get(@PathVariable UUID id) {
        return service.get(id);
    }
    
    @RequestMapping(value = "/search/{key}", method = RequestMethod.GET)
    public List<Category> getByName(@PathVariable String key) {
        return service.getCategoryByName(key);
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Category> getByNameParam(@RequestParam String key) {
        return service.getCategoryByName(key);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category> add(@RequestBody Category category, 
            UriComponentsBuilder ucb) {
        Category c = service.add(category);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/v1/categories/")
                .path(c.getId().toString())
                .build()
                .toUri();
        headers.setLocation(locationUri);
        
        return new ResponseEntity<Category>(c, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Category update(@PathVariable UUID id, 
            @RequestBody Category category) {
        category.setId(id);
        
        return service.update(category);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable UUID id) {
        service.remove(id);
    }
}
