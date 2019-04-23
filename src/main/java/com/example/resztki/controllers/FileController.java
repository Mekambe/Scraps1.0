package com.example.resztki.controllers;

import com.example.resztki.dao.CustomerRepository;
import com.example.resztki.dao.ImageRepository;
import com.example.resztki.domain.CustomerDomain;
import com.example.resztki.domain.ImageDomain;
import com.example.resztki.payload.UploadFileResponse;
import com.example.resztki.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

    @RestController
    public class FileController {

        private static final Logger logger = LoggerFactory.getLogger(FileController.class);


        private FileStorageService fileStorageService;
        private CustomerRepository customerRepository;
        private ImageRepository imageRepository;

@Autowired
        public FileController(FileStorageService fileStorageService, CustomerRepository customerRepository, ImageRepository imageRepository) {
            this.fileStorageService = fileStorageService;
            this.customerRepository = customerRepository;
            this.imageRepository = imageRepository;
        }


        @PostMapping("/uploadFile")
        public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
            String fileName = fileStorageService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();

            return new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());
        }


        @PostMapping("/uploadMultipleFiles")
        public void uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
                                        @RequestParam("id") int id) {
            List<UploadFileResponse> collect = Arrays.stream(files)
                    .map(this::uploadFile)
                    .collect(Collectors.toList());

            Optional<CustomerDomain> byId = customerRepository.findById(id);

            ImageDomain imageDomain = new ImageDomain();

            imageDomain.setImage(collect.get(0).getFileDownloadUri());

            byId.get().setImage(imageRepository.save(imageDomain));
            customerRepository.save(byId.get());



        }

        @GetMapping("/downloadFile/{fileName:.+}")
        public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
            // Load file as Resource
            Resource resource = fileStorageService.loadFileAsResource(fileName);

            // Try to determine file's content type
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException ex) {
                logger.info("Could not determine file type.");
            }

            // Fallback to the default content type if type could not be determined
            if(contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        }

//        @DeleteMapping("/downloadFile/delete/{imageID}")
//        public void deleteImage (@PathVariable("imageID") Integer id) throws IdNotFound {
//            Optional<QuestionsDomain> byIdQuestions = Optional.ofNullable(questionsDomainRepository.findByIdQuestions(id));
//            Optional<AnwsersImageAndLinks> image = Optional.ofNullable(byIdQuestions.get().getImage());
//            if (!byIdQuestions.isPresent()||!image.isPresent()){
//                throw new IdNotFound();}
//
//            byIdQuestions.get().setImage(null);
//            questionsDomainRepository.save(byIdQuestions.get());
//            anwsersImageAndLinksRepository.delete(image.get());
//
//        }



    }

