//package com.example.demo.services;
//
//import java.awt.Color;
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletResponse;
//
//import com.example.demo.model.Candidat;
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Element;
//import com.lowagie.text.Font;
//import com.lowagie.text.FontFactory;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;
//
//
//public class CandidatPDFExporter {
//	private Candidat c;
//
//    private Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//	private void write(Document document) {
//		font.setSize(18);
//		font.setColor(Color.BLUE);
//
//        Paragraph p = new Paragraph("This is centered text");
//        p.setAlignment(Element.ALIGN_LEFT);
//        p.setFont(font);
//        document.add(p);
//        Paragraph p1 = new Paragraph("Nom "+c.getNom() );
//        p.setAlignment(Element.ALIGN_LEFT);
//        document.add(p1);
//        Paragraph p2 = new Paragraph("Prenom"+c.getPrenom() );
//        p.setAlignment(Element.ALIGN_LEFT);
//        document.add(p2);
//        Paragraph p3 = new Paragraph("Nom "+c.getNom() );
//        p.setAlignment(Element.ALIGN_LEFT);
//        document.add(p3);
//        Paragraph p4 = new Paragraph("Nom "+c.getNom() );
//        p.setAlignment(Element.ALIGN_LEFT);
//        document.add(p4);
//        Paragraph p5 = new Paragraph("Nom "+c.getNom() );
//        p.setAlignment(Element.ALIGN_LEFT);
//        document.add(p5);
//
//	}
//
//	public void export(HttpServletResponse response) throws DocumentException, IOException {
//		Document document = new Document(PageSize.A4);
//		PdfWriter.getInstance(document, response.getOutputStream());
//		document.open();
//		font.setSize(18);
//		font.setColor(Color.BLUE);
//		Paragraph p = new Paragraph("Candidature", font);
//		p.setAlignment(Paragraph.ALIGN_CENTER);
//		document.add(p);
//		PdfPTable table = new PdfPTable(5);
//		table.setWidthPercentage(100f);
//		table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
//		table.setSpacingBefore(10);
//		write(document);
//		document.add(table);
//		document.close();
//		}
//
//	public CandidatPDFExporter(Candidat c) {
//		this.c = c;
//	}
//}
