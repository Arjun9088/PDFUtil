package com.arjun.pdfutil.services;

import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

@Service
public class PDFTextService {

	private final SpreadsheetExtractionAlgorithm spreadsheetExtractionAlgorithm;

	public PDFTextService(final SpreadsheetExtractionAlgorithm spreadsheetExtractionAlgorithm) {
		this.spreadsheetExtractionAlgorithm = spreadsheetExtractionAlgorithm;
	}

	public String[] extractTextFromPdf(String filename) {
		System.out.println("Extracting text....");
		try (PDDocument document = PDDocument.load(new File("/home/sabs/pdfUtilDirectory/" + filename + ".pdf"))) {
			System.out.println(document.getNumberOfPages() + " Pages");
			PDFTextStripper textStripper = new PDFTextStripper();
			String pdfText = textStripper.getText(document);
			String[] lines = pdfText.split("\\r?\\n");
			return lines;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String extractTables(String filename) {
		System.out.println("Extracting tables using tabula...");
		try (PDDocument document = PDDocument.load(new File("/home/sabs/pdfUtilDirectory/" + filename + ".pdf"))) {
			int totalPages = document.getNumberOfPages();
			System.out.println(filename + " has " + totalPages + " pages in total");
			ObjectExtractor objectExtractor = new ObjectExtractor(document);
			Page page = objectExtractor.extract(1);
			List<Table> table = spreadsheetExtractionAlgorithm.extract(page);
			for (Table tables : table) {
				List<List<RectangularTextContainer>> rows = tables.getRows();
				for (int i = 0; i < rows.size(); i++) {
					List<RectangularTextContainer> cells = rows.get(i);
					for (int j = 0; j < cells.size(); j++) {
						System.out.print(cells.get(j).getText() + "|");
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Nice";
	}

	public String extractImages(String filename) {
		System.out.println("Extracting images...");
		try (PDDocument document = PDDocument.load(new File("/home/sabs/pdfUtilDirectory/" + filename + ".pdf"))) 
		{
				document.getPages().forEach(page ->{
				PDResources pdResources = page.getResources();
				int imageCounter = 1;
				for (COSName name : pdResources.getXObjectNames()) 
				{
					try
					{
					PDXObject o = pdResources.getXObject(name);
					if (o instanceof PDImageXObject) 
					{
						PDImageXObject image = (PDImageXObject) o;
						String imgName = "/home/sabs/pdfUtilDirectory/imgs/"+ filename +"_"+ imageCounter + ".jpg";
						ImageIO.write(image.getImage(), "JPEG", new File(imgName));
						imageCounter++;
						System.out.println("An image saved");
					}
				}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Image extracted";
	}

}
