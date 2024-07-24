package MidtermExam.Group2.service.impl;

import MidtermExam.Group2.dto.InvoiceProductDTO;
import MidtermExam.Group2.entity.InvoiceProduct;
import MidtermExam.Group2.mapper.InvoiceProductMapper;
import MidtermExam.Group2.repository.InvoiceProductRepository;
import MidtermExam.Group2.repository.InvoiceRepository;
import MidtermExam.Group2.repository.ProductRepository;
import MidtermExam.Group2.entity.Invoice;
import MidtermExam.Group2.entity.Product;
import MidtermExam.Group2.service.InvoiceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {
    private final InvoiceProductRepository invoiceProductRepository;
    private final InvoiceProductMapper invoiceProductMapper;
    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;

    @Autowired
    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository, InvoiceProductMapper invoiceProductMapper, InvoiceRepository invoiceRepository, ProductRepository productRepository) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.invoiceProductMapper = invoiceProductMapper;
        this.invoiceRepository = invoiceRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Page<InvoiceProductDTO> getAllInvoiceProducts(Pageable pageable) {
        return invoiceProductRepository.findAll(pageable).map(invoiceProductMapper::toInvoiceProductDTO);
    }

    @Override
    public InvoiceProductDTO addInvoiceProduct(InvoiceProductDTO invoiceProductDTO) {
        UUID productId = invoiceProductDTO.getProductId();
        UUID invoiceId = invoiceProductDTO.getInvoiceId();

        Optional<Invoice> invoiceOpt = invoiceRepository.findById(invoiceProductDTO.getInvoiceId());
        Optional<Product> productOpt = productRepository.findById(invoiceProductDTO.getProductId());

        Product product = productOpt.get();
        if (!"ACTIVE".equals(product.getStatus())) {
            throw new IllegalArgumentException("Product is not active");
        }

        if (invoiceOpt.isEmpty() || productOpt.isEmpty()) {
            throw new IllegalArgumentException("Invoice or Product not found");
        }

        Invoice invoice = invoiceOpt.get();

        BigDecimal amount = product.getPrice().multiply(BigDecimal.valueOf(invoiceProductDTO.getQuantity()));

        InvoiceProduct invoiceProduct = invoiceProductMapper.toInvoiceProduct(invoiceProductDTO, invoice, product);
        invoiceProduct.setProductName(product.getName());
        invoiceProduct.setAmount(amount);
        invoiceProduct.setCreatedTime(LocalDateTime.now());
        invoiceProduct.setUpdatedTime(LocalDateTime.now());
        invoiceProduct = invoiceProductRepository.save(invoiceProduct);

        BigDecimal totalAmount = invoiceProductRepository.calculateTotalAmountByInvoiceId(invoice.getId());
        invoice.setInvoiceAmount(totalAmount);
        invoiceRepository.save(invoice);

        return invoiceProductMapper.toInvoiceProductDTO(invoiceProduct);
    }
}
