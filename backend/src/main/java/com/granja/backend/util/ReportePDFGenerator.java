package com.granja.backend.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.granja.backend.dto.ClientePorcinoDTO;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ReportePDFGenerator {
    public static ByteArrayInputStream clientesPorcinosToPDF(List<ClientePorcinoDTO> data) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 3, 3, 3, 2, 1, 2});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            String[] headers = {"Cédula", "Nombres", "Apellidos", "Identificación Porcino", "Raza", "Edad", "Peso"};
            for (String h : headers) {
                PdfPCell hcell = new PdfPCell(new Phrase(h, headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);
            }

            for (ClientePorcinoDTO item : data) {
                table.addCell(item.getClienteCedula());
                table.addCell(item.getClienteNombres());
                table.addCell(item.getClienteApellidos());
                table.addCell(item.getPorcinoIdentificacion());
                table.addCell(item.getRaza());
                table.addCell(item.getEdad() != null ? item.getEdad().toString() : "");
                table.addCell(item.getPeso() != null ? item.getPeso().toString() : "");
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Reporte de Clientes y Porcinos"));
            document.add(Chunk.NEWLINE);
            document.add(table);
            document.close();
        } catch (Exception ex) {
            throw new RuntimeException("Error generando PDF", ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
