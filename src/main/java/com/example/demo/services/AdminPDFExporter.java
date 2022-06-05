package com.example.demo.services;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.Candidat;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class AdminPDFExporter {
	private List<Candidat> listUsers;
	
	
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		cell.setPhrase(new Phrase("CIN", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Nom", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Prenom", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Delegation", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Téléponé", font));
		table.addCell(cell);
	}
	private void writeTableData(PdfPTable table) {
		for (Candidat user : listUsers) {
		table.addCell(user.getCin());
		table.addCell(user.getNom());
		table.addCell(user.getPrenom());
		table.addCell(user.getDelegation());
		table.addCell(user.getTel());
		
		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		Paragraph p = new Paragraph("Listes des Candidats", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
		table.setSpacingBefore(10);
		writeTableHeader(table);
		writeTableData(table);
		document.add(table);
		document.close();
		}
}