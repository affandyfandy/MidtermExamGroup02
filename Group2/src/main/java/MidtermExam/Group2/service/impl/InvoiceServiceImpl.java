package MidtermExam.Group2.service.impl;

import MidtermExam.Group2.criteria.InvoiceSearchCriteria;
import MidtermExam.Group2.dto.InvoiceListDTO;
import MidtermExam.Group2.dto.InvoicesDTO;
import MidtermExam.Group2.entity.Customer;
import MidtermExam.Group2.entity.Invoice;
import MidtermExam.Group2.mapper.InvoiceMapper;
import MidtermExam.Group2.repository.CustomerRepository;
import MidtermExam.Group2.repository.InvoiceRepository;
import MidtermExam.Group2.repository.InvoiceSpecification;
import MidtermExam.Group2.service.InvoiceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final CustomerRepository customerRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRepository customerRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<InvoiceListDTO> getAllInvoices(Pageable pageable, InvoiceSearchCriteria criteria) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(criteria);
        return invoiceRepository.findAll(invoiceSpecification, pageable).map(invoiceMapper::toInvoiceListDTO);
    }

    @Override
    public InvoicesDTO addInvoice(InvoicesDTO invoicesDTO) {
        UUID customerId = invoicesDTO.getCustomerId();
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            throw new IllegalArgumentException("Customer not found");
        }

        Customer customer = customerOpt.get();

        Invoice invoice = invoiceMapper.toInvoices(invoicesDTO);

        LocalDate invoiceDateOnly = invoicesDTO.getInvoiceDate();
        invoice.setInvoiceDate(LocalDateTime.of(invoiceDateOnly, LocalTime.MIDNIGHT));

        if (invoice.getInvoiceAmount() == null) {
            invoice.setInvoiceAmount(BigDecimal.ZERO);
        }

        invoice.setCustomer(customer);
        invoice.setCreatedTime(LocalDateTime.now());
        invoice.setUpdatedTime(LocalDateTime.now());
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.toInvoicesDTO(invoice);
    }
}
