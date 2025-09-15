package com.granja.backend.controller;

import com.granja.backend.dto.ClientePorcinoDTO;
import com.granja.backend.entity.Porcino;
import com.granja.backend.repository.PorcinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.granja.backend.util.ReportePDFGenerator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reporte-clientes-porcinos")
public class ReporteController {
    @Autowired
    private PorcinoRepository porcinoRepository;

    @GetMapping
    public List<ClientePorcinoDTO> getReporte() {
    return porcinoRepository.findAll().stream()
        .filter(p -> p.getCliente() != null)
        .map(p -> new ClientePorcinoDTO(
            p.getCliente().getCedula(),
            p.getCliente().getNombres(),
            p.getCliente().getApellidos(),
            p.getIdentificacion(),
            p.getRaza(),
            p.getEdad(),
            p.getPeso()
        ))
        .collect(Collectors.toList());
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> getReportePDF() {
    List<ClientePorcinoDTO> data = getReporte();
    byte[] pdfBytes = ReportePDFGenerator.clientesPorcinosToPDF(data).readAllBytes();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.setContentDispositionFormData("attachment", "reporte_clientes_porcinos.pdf");
    return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}
