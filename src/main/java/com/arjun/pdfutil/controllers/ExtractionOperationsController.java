package com.arjun.pdfutil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.pdfutil.services.PDFTextService;

@RestController
@RequestMapping("/")
public class ExtractionOperationsController 
{
	private PDFTextService pdfTextService;
	
	
	@Autowired
	public void setPdfTextService(PDFTextService pdfTextService) {
		this.pdfTextService = pdfTextService;
	}



	@GetMapping("/extractText/{filename:.+}")
	public ResponseEntity<String[]> getPDFText(@PathVariable String filename) 
	{
		return new ResponseEntity<String[]>(pdfTextService.extractTextFromPdf(filename),HttpStatus.OK);
	}
}
