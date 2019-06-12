package com.example.resztki.controllers;


import com.example.resztki.dao.UsersRepository;
import com.example.resztki.domain.DBFile;

import com.example.resztki.domain.UsersDomain;
import com.example.resztki.payload.UploadFileResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.Arrays;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;







import org.springframework.core.io.ByteArrayResource;



import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    UsersRepository usersRepository;
    @Autowired
    public FileController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Autowired
    private com.example.resztki.service.DBFileStorageService DBFileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        DBFile dbFile = DBFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    @PostMapping("/uploadFileWithCustomer")
    public ResponseEntity.BodyBuilder uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("customerID") Integer id) {

        Optional<UsersDomain> byId = usersRepository.findById(id);
        if (byId.isPresent()) {

            DBFile dbFile = DBFileStorageService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(dbFile.getId())
                    .toUriString();

            return ResponseEntity.status(HttpStatus.ACCEPTED);
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        DBFile dbFile = DBFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }


}
