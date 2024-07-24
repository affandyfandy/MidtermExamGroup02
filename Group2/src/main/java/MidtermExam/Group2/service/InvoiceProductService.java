package MidtermExam.Group2.service;

import MidtermExam.Group2.dto.InvoiceProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InvoiceProductService {
    Page<InvoiceProductDTO> getAllInvoiceProducts(Pageable pageable);
}
