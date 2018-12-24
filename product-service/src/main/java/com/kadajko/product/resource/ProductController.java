package com.kadajko.product.resource;

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

import com.kadajko.product.domain.model.Product;
import com.kadajko.product.domain.service.ProductService;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public List<Product> getAll() {
        return productService.getAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public Product get(@PathVariable UUID id) {
        return productService.get(id);
    }
    
    @RequestMapping(value = "/size/{size}/page/{page}", 
            method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public List<Product> getByPage(@PathVariable int size, 
            @PathVariable int page) {
        return productService.getByPage(size, page);
    }
    
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public List<Product> getByCategory(@PathVariable UUID id) {
        return productService.getByCategory(id);
    }
    
    @RequestMapping(value = "/category/{id}/size/{size}/page/{page}", 
            method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public List<Product> getByCategoryAndPage(@PathVariable UUID id, 
            @PathVariable int size, @PathVariable int page) {
        return productService.getByCategoryAndPage(id, size, page);
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public List<Product> getByName(@RequestParam String key) {
        return productService.getByName(key);
    }
    
    @RequestMapping(value = "/search/{key}/size/{size}/page/{page}", 
            method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public List<Product> getByNameAndPage(@PathVariable String key, 
            @PathVariable int size, @PathVariable int page) {
        return productService.getByNameAndPage(key, size, page);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Product> add(@RequestBody Product product, 
            UriComponentsBuilder ucb) {
        product = productService.add(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucb
                .path("/v1/products/")
                .path(product.getId().toString())
                .build()
                .toUri());
        
        return new ResponseEntity<Product>(
                product, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "*")
    public Product update(@PathVariable UUID id, @RequestBody Product product) {
        product.setId(id);
        return productService.update(product);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "*")
    public void remove(@PathVariable UUID id) {
        productService.remove(id);
    }
}