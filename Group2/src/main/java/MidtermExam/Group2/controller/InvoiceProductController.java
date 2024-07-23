package MidtermExam.Group2.controller;

import MidtermExam.Group2.dto.InvoiceProductDTO;
import MidtermExam.Group2.service.InvoiceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoice-products")
public class InvoiceProductController {
    private final InvoiceProductService invoiceProductService;

    @Autowired
    public InvoiceProductController(InvoiceProductService invoiceProductService) {
        this.invoiceProductService = invoiceProductService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceProductDTO>> getAllInvoiceProducts() {
        return ResponseEntity.ok(invoiceProductService.getAllInvoiceProducts());
    }
}
