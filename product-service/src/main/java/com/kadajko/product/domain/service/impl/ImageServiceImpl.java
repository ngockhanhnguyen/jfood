package com.kadajko.product.domain.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kadajko.product.domain.model.Image;
import com.kadajko.product.domain.repository.ImageRepository;
import com.kadajko.product.domain.service.ImageService;
import com.kadajko.product.exception.UnknownResourceException;

@Service
public class ImageServiceImpl implements ImageService {
    
    @Autowired
    private ImageRepository repository;

    @Override
    public Image get(UUID id) {
        Image image = repository.get(id);
        if (image == null) 
            throw new UnknownResourceException(
                    "Does not exist Image with id: " + id);
        return image;
    }

    @Override
    public Image add(Image image, MultipartFile file) {
        return repository.add(image, file);
    }

    @Override
    public Image add(Image image) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Resource getImageAsResource(UUID id) {
        return repository.getImageAsResource(id);
    }

    @Override
    public List<Image> getAll() {
        return repository.getAll();
    }

}
