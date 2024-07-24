package MidtermExam.Group2.controller;

import MidtermExam.Group2.criteria.InvoiceSearchCriteria;
import MidtermExam.Group2.dto.InvoiceDTO;
import MidtermExam.Group2.dto.InvoiceListDTO;
import MidtermExam.Group2.service.InvoiceService;
import jakarta.validation.Valid;
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

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> addInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO addedInvoice = invoiceService.addInvoice(invoiceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedInvoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> editInvoice(@PathVariable("id") UUID id, @Valid @RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO editedInvoice = invoiceService.editInvoice(id, invoiceDTO);
        return ResponseEntity.ok(editedInvoice);
    }
}
