package com.jorder.bank.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jorder.bank.config.FileProperties;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/files")
public class FileStorageController {
    
    private final Path fileStorageLocation;

    public FileStorageController(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir())
            .toAbsolutePath()
            .normalize();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path targetLocation = fileStorageLocation.resolve(fileName);
            file.transferTo(targetLocation);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("files/download?filename=")
                .path(fileName)
                .toUriString();
            return ResponseEntity.ok("Upload completed. Download link: " + fileDownloadUri);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("filename") String fileName, HttpServletRequest request) {
        System.out.println(fileName);
        Path filePath  = fileStorageLocation.resolve(fileName).normalize();
        try {
            Resource resource = new UrlResource(filePath.toUri());
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        } 
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        try {
            List<String> fileNames = Files.list(fileStorageLocation)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
            return ResponseEntity.ok(fileNames);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
}
