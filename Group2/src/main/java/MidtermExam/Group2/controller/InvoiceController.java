package MidtermExam.Group2.controller;

import MidtermExam.Group2.criteria.InvoiceSearchCriteria;
import MidtermExam.Group2.dto.InvoiceListDTO;
import MidtermExam.Group2.service.ExportService;
import MidtermExam.Group2.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final ExportService exportService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, ExportService exportService) {
        this.invoiceService = invoiceService;
        this.exportService = exportService;
    }

    @GetMapping
    public ResponseEntity<Page<InvoiceListDTO>> getAllInvoices(Pageable pageable, InvoiceSearchCriteria criteria) {
        return ResponseEntity.ok(invoiceService.getAllInvoices(pageable, criteria));
    }

    @GetMapping("/export")
    public ResponseEntity<?> exportInvoicesToExcel(@RequestParam(required = false) UUID customerId,
                                                   @RequestParam(required = false) Integer month,
                                                   @RequestParam(required = false) Integer year) {
        try {
            ByteArrayInputStream excelFile = exportService.exportInvoicesToExcel(customerId, month, year);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=invoices.xlsx");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                    .body(excelFile.readAllBytes());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to export invoices to Excel");
        }
    }
}
