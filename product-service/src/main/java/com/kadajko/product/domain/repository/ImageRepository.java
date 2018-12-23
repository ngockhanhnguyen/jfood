package com.kadajko.product.domain.repository;

import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.kadajko.product.domain.model.Image;

public interface ImageRepository extends BaseRepository<Image, UUID> {
    Image add(Image entity, MultipartFile file);
    
    Resource getImageAsResource(UUID id);
}
