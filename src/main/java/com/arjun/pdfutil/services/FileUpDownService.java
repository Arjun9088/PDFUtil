package com.arjun.pdfutil.services;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileUpDownService 
{
	public ResponseEntity<String> fileUploadService(MultipartFile file) throws IOException;
	public ResponseEntity<Resource>  fileDownloadService(String filename) throws MalformedURLException;
	
}
