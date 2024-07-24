package MidtermExam.Group2.controller;

import MidtermExam.Group2.criteria.InvoiceSearchCriteria;
import MidtermExam.Group2.dto.CustomerDTO;
import MidtermExam.Group2.dto.InvoiceListDTO;
import MidtermExam.Group2.dto.InvoicesDTO;
import MidtermExam.Group2.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<InvoicesDTO> addInvoice(@RequestBody InvoicesDTO invoicesDTO) {
        InvoicesDTO addedInvoice = invoiceService.addInvoice(invoicesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedInvoice);
    }
}
