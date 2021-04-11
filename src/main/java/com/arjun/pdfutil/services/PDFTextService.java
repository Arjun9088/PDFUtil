package com.arjun.pdfutil.services;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;

public class PDFTextService 
{
	public  String extractTextFromPdf(String filename)
	{
		try(PDDocument document =  PDDocument.load(new File("/home/sabs/"+filename));)
		{
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "Test";
	}
}
