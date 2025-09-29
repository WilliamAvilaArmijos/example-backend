package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.impl;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.pichincha.financial.instruction.application.input.port.MovementInputPort;
import com.pichincha.financial.instruction.infraestructure.output.repository.AccountRepository;
import com.pichincha.financial.instruction.infraestructure.output.repository.ClientRepository;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.ReportData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReportController {

    private final MovementInputPort movementInputPort;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<?> getReportePorFechas(
            @RequestParam Integer clienteId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin,
            @RequestParam(required = false) String format
    ) {
        LocalDateTime inicio = LocalDate.parse(fechaInicio).atStartOfDay();
        LocalDateTime fin = LocalDate.parse(fechaFin).atTime(23, 59, 59);

        var cliente = clientRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        var cuentas = accountRepository.findByClientId(clienteId);
        List<ReportData> reporte = new ArrayList<>();

        for (var cuenta : cuentas) {
            var initBalance = movementInputPort.calculateInitBalanceBefore(cuenta.getId(), inicio);
            BigDecimal currentBalance = initBalance;
            var movimientos = movementInputPort.findByAccountAndDateBetween(cuenta.getId(), inicio, fin);
            for (var mov : movimientos) {
                var data = new ReportData();
                data.setDate(mov.getDate().toLocalDate().toString());
                data.setClient(cliente.getName());
                data.setAccountNumber(cuenta.getAccountNumber());
                data.setAccountType(cuenta.getAccountType());
                data.setInitBalance(currentBalance);
                data.setStatus(cuenta.getStatus());
                data.setAmount(mov.getAmount());
                BigDecimal newBalance;
                if ("CREDITO".equalsIgnoreCase(mov.getType())) {
                    newBalance = currentBalance.add(mov.getAmount());
                } else {
                    newBalance = currentBalance.subtract(mov.getAmount());
                }
                data.setBalance(newBalance);
                currentBalance = newBalance;
                reporte.add(data);
            }
        }

        // Si NO se solicita PDF, devuelve JSON como antes
        if (format == null || !format.equalsIgnoreCase("pdf")) {
            return ResponseEntity.ok(reporte);
        }

        // Si se solicita formato PDF
        byte[] pdf = generateSimplePdf(reporte); // Generador interno

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename("reporte.pdf").build());

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }

    private byte[] generateSimplePdf(List<ReportData> reportes) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Reporte de Movimientos")
                    .setBold()
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20));

            float[] colWidths = {80, 80, 100, 70, 80, 50, 80, 80};
            Table table = new Table(colWidths);
            table.setWidth(UnitValue.createPercentValue(100));

            // Encabezados
            String[] headers = {
                    "Fecha", "Cliente", "N° Cuenta", "Tipo", "Saldo Inicial",
                    "Estado", "Movimiento", "Saldo Final"
            };
            for (String h : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(h).setBold()));
            }

            // Datos
            for (ReportData r : reportes) {
                table.addCell(r.getDate());
                table.addCell(r.getClient());
                table.addCell(r.getAccountNumber());
                table.addCell(r.getAccountType());
                table.addCell(r.getInitBalance().toString());
                table.addCell(r.getStatus());
                table.addCell(r.getAmount().toString());
                table.addCell(r.getBalance().toString());
            }

            document.add(table);
            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }



}
