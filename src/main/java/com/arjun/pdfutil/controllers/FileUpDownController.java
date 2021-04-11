package com.arjun.pdfutil.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class FileUpDownController 
{
	
	@PostMapping("/uploadFile")
	public ResponseEntity<String> uploadFile()
	{
		
		return new ResponseEntity<String>("File upload successful", HttpStatus.OK);
	}
}