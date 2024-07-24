package MidtermExam.Group2.controller;

import MidtermExam.Group2.dto.InvoiceListDTO;
import MidtermExam.Group2.dto.InvoiceProductDTO;
import MidtermExam.Group2.service.InvoiceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<InvoiceProductDTO>> getAllInvoiceProducts(Pageable pageable) {
        return ResponseEntity.ok(invoiceProductService.getAllInvoiceProducts(pageable));
    }

    @PostMapping
    public ResponseEntity<InvoiceProductDTO> addInvoiceProduct(@RequestBody InvoiceProductDTO invoiceProductDTO) {
        InvoiceProductDTO addedInvoiceProduct = invoiceProductService.addInvoiceProduct(invoiceProductDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedInvoiceProduct);
    }
}
