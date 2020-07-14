package com.example.demo.util;

import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.example.demo.model.Consommation;

import io.jsonwebtoken.io.IOException;
/*classe de creation du pdf*/
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
		
        //initialisation
		//nombre de lignes
		int rows = consommations.size();
		//nombre de colonnes
		int cols = 2;
		//marge entre bord de la feuille et le tableau
		int margin = 100;
		//point de depart des ordonnees (le point de coordonnee (0,0) est le coin bas gauche de la page)
		int y = 700;
		//hauteur de colonne
		float rowHeight = 20f;
		//largeur de tableau
		float tableWidth = page.findMediaBox().getWidth() - (2 * margin);
		//hauteur de tableau
		float tableHeight = rowHeight * rows;
		//largeur de colonne
		float colWidth = tableWidth / (float) cols;
		//marge entre bordure de tableau et texte
		float cellMargin = 5f;

		// Dessiner les Lignes
		float nexty = y;
		for (int i = 0; i <= rows; i++) {
			contentStream.drawLine(margin, nexty, margin + tableWidth, nexty);
			nexty -= rowHeight;
		}

		// Dessiner les Colonnes
		float nextx = margin;
		for (int i = 0; i <= cols; i++) {
			contentStream.drawLine(nextx, y, nextx, y - tableHeight);
			nextx += colWidth;
		}

		// Titre
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(colWidth, y + 2 * rowHeight);
		contentStream.drawString("Consommations pour la Semaine : " + consommations.get(0).getNumSemaine());
		contentStream.endText();
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
		
		// insertion de texte ligne par ligne
		float textx = margin + cellMargin;
		float texty = y - rowHeight/2;
		String text;
		for (Consommation c : consommations) {
			//Dessin d'une ligne donnee
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
				//passage la 2nd colonne de la meme ligne
				textx += colWidth;
			}
			
			texty -= rowHeight;
			textx = margin + cellMargin;

		}
		contentStream.close();
	}

}
