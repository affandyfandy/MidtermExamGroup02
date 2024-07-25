package MidtermExam.Group2.controller;

import MidtermExam.Group2.criteria.InvoiceSearchCriteria;
import MidtermExam.Group2.dto.InvoiceDTO;
import MidtermExam.Group2.dto.InvoiceDetailDTO;
import MidtermExam.Group2.dto.InvoiceListDTO;
import MidtermExam.Group2.service.InvoiceService;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<Page<InvoiceListDTO>> getAllInvoices(Pageable pageable, InvoiceSearchCriteria criteria) {
        return ResponseEntity.ok(invoiceService.getAllInvoices(pageable, criteria));
    }

    @PostMapping
    public ResponseEntity<?> addInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
        try {
            InvoiceDTO addedInvoice = invoiceService.addInvoice(invoiceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedInvoice);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("errors", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editInvoice(@PathVariable("id") UUID id,
            @Valid @RequestBody InvoiceDTO invoiceDTO) {
        try {
            InvoiceDTO editedInvoice = invoiceService.editInvoice(id, invoiceDTO);
            return ResponseEntity.ok(editedInvoice);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("errors", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceDetail(@PathVariable("id") UUID invoiceId) {
        try {
            InvoiceDetailDTO invoiceDetail = invoiceService.getInvoiceDetail(invoiceId);
            return new ResponseEntity<>(invoiceDetail, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("errors", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
