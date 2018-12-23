package com.kadajko.product.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.kadajko.product.domain.model.Image;

public interface ImageService {
    Image add(Image image, MultipartFile file);
    
    Image add(Image image);
    
    Image get(UUID id);
    
    List<Image> getAll();
    
    Resource getImageAsResource(UUID id);
}
