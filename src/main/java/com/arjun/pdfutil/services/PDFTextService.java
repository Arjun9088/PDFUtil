package com.arjun.pdfutil.services;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

@Service
public class PDFTextService 
{
	public  String[] extractTextFromPdf(String filename)
	{
		System.out.println("Extracting text....");
		try(PDDocument document =  PDDocument.load(new File("/home/sabs/pdfUtilDirectory/"+filename+".pdf"));)
		{
			PDFTextStripper textStripper = new PDFTextStripper();
			String pdfText = textStripper.getText(document);
			String [] lines = pdfText.split("\\r?\\n");
			return lines;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
