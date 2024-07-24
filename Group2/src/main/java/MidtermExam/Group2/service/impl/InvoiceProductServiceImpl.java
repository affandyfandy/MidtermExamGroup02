package MidtermExam.Group2.service.impl;

import MidtermExam.Group2.dto.InvoiceProductDTO;
import MidtermExam.Group2.entity.InvoiceProduct;
import MidtermExam.Group2.mapper.InvoiceProductMapper;
import MidtermExam.Group2.repository.InvoiceProductRepository;
import MidtermExam.Group2.service.InvoiceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {
    private final InvoiceProductRepository invoiceProductRepository;
    private final InvoiceProductMapper invoiceProductMapper;

    @Autowired
    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository,
            InvoiceProductMapper invoiceProductMapper) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.invoiceProductMapper = invoiceProductMapper;
    }

    public Page<InvoiceProductDTO> getAllInvoiceProducts(Pageable pageable) {
        return invoiceProductRepository.findAll(pageable).map(invoiceProductMapper::toInvoiceProductDTO);
    }

}
