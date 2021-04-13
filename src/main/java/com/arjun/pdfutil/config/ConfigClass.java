package com.arjun.pdfutil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import technology.tabula.detectors.SpreadsheetDetectionAlgorithm;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

@Configuration
public class ConfigClass 
{
	
	@Bean
	public SpreadsheetExtractionAlgorithm spreadSheetExt()
	{
		return new SpreadsheetExtractionAlgorithm();
	}
}
