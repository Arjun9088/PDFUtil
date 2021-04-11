package com.arjun.pdfutil.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalServerStorageService implements FileUpDownService
{
	@Value("${app.upload.dir:${user.home}}")
	private String storeDirectory;
	
	public ResponseEntity<String> fileUploadService(MultipartFile file) throws IOException 
	{

		
		 Path storeLocation = Paths.get(storeDirectory + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
		 Files.copy(file.getInputStream(),storeLocation, StandardCopyOption.REPLACE_EXISTING);
		 return new ResponseEntity<String>("File Uploaded", HttpStatus.OK);
	}

	public ResponseEntity<Resource> fileDownloadService(String filename) throws MalformedURLException 
	{
		Path path = Paths.get("/home/sabs/pdfUtilDirectory/" + filename);
		Resource resource = null;
		resource = new UrlResource(path.toUri());
		String contentType = "application/octet-stream";
		
		return ResponseEntity.ok()
							 .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
							 .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + resource.getFilename() + "\"")
							 .body(resource);
		
	}

}
