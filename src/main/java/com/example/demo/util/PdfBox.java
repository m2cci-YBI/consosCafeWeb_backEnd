package com.example.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import io.jsonwebtoken.io.IOException;

public class PdfBox  {
	
	PDDocument document;
	
	public PdfBox(PDDocument document) {
		super();
		this.document = document;
	}



	public void write() throws IOException, java.io.IOException {
		
		
        PDPage page = new PDPage();
        document.addPage(page);
		
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		
		
	
		contentStream.beginText();
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 13 );
		contentStream.newLineAtOffset(25, 500);
		contentStream.showText( "Hello World" );
		contentStream.endText();
		contentStream.close();
		
				
	  
	   
		
	     
	} 
	   
	
	 
	
	 

}
