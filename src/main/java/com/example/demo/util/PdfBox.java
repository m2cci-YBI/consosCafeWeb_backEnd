package com.example.demo.util;

import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.example.demo.model.Consommation;

import io.jsonwebtoken.io.IOException;

public class PdfBox {

	PDDocument document;

	public PdfBox(PDDocument document) {
		super();
		this.document = document;
	}

	public void write(List<Consommation> consommations) throws IOException, java.io.IOException {

		PDPage page = new PDPage();
		document.addPage(page);
		PDPageContentStream contentStream = new PDPageContentStream(document, page);

		int rows = consommations.size();
		int cols = 2;
		int margin = 100;
		int y = 700;
		float rowHeight = 20f;
		float tableWidth = page.findMediaBox().getWidth() - (2 * margin);
		float tableHeight = rowHeight * rows;
		float colWidth = tableWidth / (float) cols;
		float cellMargin = 5f;

		// les Lignes
		float nexty = y;
		for (int i = 0; i <= rows; i++) {
			contentStream.drawLine(margin, nexty, margin + tableWidth, nexty);
			nexty -= rowHeight;
		}

		// les Colonnes
		float nextx = margin;
		for (int i = 0; i <= cols; i++) {
			contentStream.drawLine(nextx, y, nextx, y - tableHeight);
			nextx += colWidth;
		}

		// font de texte
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

		// insertion de texte ligne puis colonne
		float textx = margin + cellMargin;
		float texty = y - 15;
		String text;
		for (Consommation c : consommations) {
			for (int j = 0; j < cols; j++) {
				if (j == 0) {
					text = c.getNomCompletProgrammeur();
				} else {
					text = "" + c.getNbTasses();
				}
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(textx, texty);
				contentStream.drawString(text);
				contentStream.endText();
				textx += colWidth;
			}
			texty -= rowHeight;
			textx = margin + cellMargin;

		}
		contentStream.close();
	}

}
