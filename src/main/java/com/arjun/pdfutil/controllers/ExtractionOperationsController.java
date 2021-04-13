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

	@GetMapping("/extractTable/{filename:.+}")
	public ResponseEntity<String> getPDFTables(@PathVariable String filename)
	{
		return new ResponseEntity<String>(pdfTextService.extractTables(filename),HttpStatus.OK);
	}

	@GetMapping("extractAll/{filename:.+}")
	public ResponseEntity<String> extract(@PathVariable String filename, @PathVariable String code)
	{
		String [] pdfText  = pdfTextService.extractTextFromPdf(filename);
		return new ResponseEntity<String>("GOod",HttpStatus.OK);
	}
	
	@GetMapping("extractImages/{filename:.+}")
	public ResponseEntity<String> extractImages(@PathVariable String filename)
	{
		return new ResponseEntity<String>(pdfTextService.extractImages(filename), HttpStatus.OK);
	}
}
