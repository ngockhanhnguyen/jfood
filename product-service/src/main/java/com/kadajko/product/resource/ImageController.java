package com.kadajko.product.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kadajko.product.domain.model.Image;
import com.kadajko.product.domain.service.ImageService;
import com.kadajko.product.exception.ImageFormatNotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/v1/images")
public class ImageController {
    
    @Autowired
    private ImageService imageService;
    
    @RequestMapping(value = "/product/{id}", method = RequestMethod.POST)
    public ResponseEntity<Image> addPrimaryImageForProduct(
            @RequestParam("primaryImage") MultipartFile primaryImage, 
            @PathVariable("id") UUID productId) {
//        if (!request.getServletContext()
//                .getMimeType(originalFilename)
//                .startsWith("image/"))
        
        Image image = this.checkImageAndSave(primaryImage, productId, true);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(image.getImageUrl()));
        
        return new ResponseEntity<Image>(image, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/other/product/{id}", method = RequestMethod.POST)
    public ResponseEntity<Image> addOtherImagesForProduct(
            @RequestParam("otherImage") MultipartFile otherImage, 
            @PathVariable("id") UUID productId) {
        Image image = this.checkImageAndSave(otherImage, productId, false);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(image.getImageUrl()));
        
        return new ResponseEntity<Image>(image, headers, HttpStatus.CREATED);
    }
    
//    @RequestMapping(value = "/other/product/{id}", method = RequestMethod.POST)
//    public ResponseEntity<List<Image>> addOtherImagesForProduct(
//            @RequestParam("otherImages") MultipartFile[] otherImages, 
//            @PathVariable("id") UUID productId) {
//        
//        List<Image> images = new ArrayList<>();
////        Arrays.stream(otherImages).forEach(image -> {
////            images.add(checkImageAndSave(image, productId, false));
////        });
//        
//        for (MultipartFile image : otherImages) {
//            addPrimaryImageForProduct(image, productId);
//        }
//        
//        return new ResponseEntity<List<Image>>(images, HttpStatus.CREATED);
//    }
    
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public ImageFormatNotFoundException addOtherImagesForProduct() {
//        
//        return new ImageFormatNotFoundException("Định dạng image không đúng");
//    }
    
    private Image checkImageAndSave(MultipartFile imageFile, UUID productId, 
            boolean isPrimary) {
        String contentType = imageFile.getContentType();
        if (!contentType.startsWith("image"))
            throw new ImageFormatNotFoundException("Định dạng image không đúng");
        
        String imageUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/v1/images/")
                .toUriString();
        // Content-Type: image/jpg,...
        String type = contentType.substring(contentType.lastIndexOf("/") + 1);
        Image image = new Image(imageUrl, isPrimary, new Date(), productId, 
                type);
        
        image = imageService.add(image, imageFile);
        return image;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getImageAsResource(@PathVariable UUID id, 
            HttpServletRequest request) {
        Resource resource = imageService.getImageAsResource(id);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(
                    resource.getFile().getAbsolutePath());
        } catch (Exception e) {
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(
                MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                "inline; filename\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
