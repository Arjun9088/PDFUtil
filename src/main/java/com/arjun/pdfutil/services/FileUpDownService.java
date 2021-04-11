package com.arjun.pdfutil.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileUpDownService 
{
	public ResponseEntity<String> fileUploadService(MultipartFile file);
	
}
