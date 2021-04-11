package com.arjun.pdfutil.controllers;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjun.pdfutil.services.FileUpDownService;

@RestController
@RequestMapping("/")
public class FileUpDownController {
	private FileUpDownService fileUpDownService;
	
	@Value("${app.upload.dir:${user.home}}")
    private String storeDirectory;
	

	@Autowired
	public void setFileUpDownService(FileUpDownService fileUpDownService) {
		this.fileUpDownService = fileUpDownService;
	}

	@PostMapping("/uploadFile")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {

//			return new ResponseEntity<String>("Please Upload a file and try again.", HttpStatus.BAD_REQUEST);
		}

		try {
			return fileUpDownService.fileUploadService(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<String>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/downloadFile/{filename:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws MalformedURLException
	{
		
		return fileUpDownService.fileDownloadService(filename);
	}
	
}
