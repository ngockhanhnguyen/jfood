package com.kadajko.product.domain.repository.impl;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.kadajko.product.domain.model.Image;
import com.kadajko.product.domain.repository.ImageRepository;
import com.kadajko.product.exception.FileNotFoundCustomException;
import com.kadajko.product.exception.FileStorageException;
import com.kadajko.product.exception.UnknownResourceException;

@Repository
@Transactional
public class ImageRepositoryImpl implements ImageRepository {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Path fileStorageLocation;
    
    public ImageRepositoryImpl(
            @Value("${file.upload-dir}") String uploadDirectoryName) {
        this.fileStorageLocation = 
                Paths.get(uploadDirectoryName)
                .toAbsolutePath().normalize();
        try {
            if (!Files.exists(fileStorageLocation))
                Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException(
                    "Could not create the directory where the uploaded files "
                    + "will be stored.", e);
        }
    }
    
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public Image add(Image entity, MultipartFile file) {
        Session session = getSession();
        entity = session.get(Image.class, session.save(entity));
        entity.setImageUrl(entity.getImageUrl() + entity.getId().toString());
        session.save(entity);
        this.save(file, entity);
        
        return entity;
    }

    @Override
    public Image get(UUID id) {
        return getSession().get(Image.class, id);
    }
    
    @Override
    public Resource getImageAsResource(UUID id) {
        Image image = getSession().get(Image.class, id);
        if (image == null)
            throw new UnknownResourceException(
                    "Does not exist Image with id: " + id);
        return this.loadImageAsResource(image);
    }
    
    private void save(MultipartFile file, Image image) {
        String fileName = image.getId().toString() + "." + image.getType();
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, 
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + 
                    fileName + ". Please try again!", e);
        }
    }
    
    private Resource loadImageAsResource(Image image) {
        String fileName = image.getId().toString() + "." + image.getType();
        try {
            Path filePath = this.fileStorageLocation
                    .resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundCustomException(
                        "File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundCustomException(
                    "File not found " + fileName, ex);
        }
    }

    @Override
    public void remove(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Image update(Image entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Image> getAll() {
        return getSession().createCriteria(Image.class).list();
    }

    @Override
    public Image add(Image entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
