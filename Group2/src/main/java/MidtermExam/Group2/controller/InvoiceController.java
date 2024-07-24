package MidtermExam.Group2.controller;

import MidtermExam.Group2.criteria.InvoiceSearchCriteria;
import MidtermExam.Group2.dto.InvoiceDetailDTO;
import MidtermExam.Group2.dto.InvoiceListDTO;
import MidtermExam.Group2.service.InvoiceService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceDetailDTO> getInvoiceDetail(@PathVariable UUID invoiceId) {
        try {
            InvoiceDetailDTO invoiceDetail = invoiceService.getInvoiceDetail(invoiceId);
            return new ResponseEntity<>(invoiceDetail, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
