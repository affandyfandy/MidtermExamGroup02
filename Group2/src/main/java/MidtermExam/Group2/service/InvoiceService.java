package MidtermExam.Group2.service;

import MidtermExam.Group2.criteria.InvoiceSearchCriteria;
import MidtermExam.Group2.dto.InvoiceAddDTO;
import MidtermExam.Group2.dto.InvoiceDTO;
import MidtermExam.Group2.dto.InvoiceDetailDTO;
import MidtermExam.Group2.dto.InvoiceListDTO;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {
    Page<InvoiceListDTO> getAllInvoices(Pageable pageable, InvoiceSearchCriteria criteria);

    List<InvoiceListDTO> getAllInvoicesList(InvoiceSearchCriteria criteria);

    InvoiceDTO getInvoiceById(UUID id);

    InvoiceDTO addSingleInvoice(InvoiceDTO invoiceDTO);

    InvoiceAddDTO addInvoice(InvoiceAddDTO invoiceAddDTO);

    InvoiceDTO editInvoice(UUID id, InvoiceDTO invoiceDTO);

    InvoiceDetailDTO getInvoiceDetail(UUID invoiceId);

    void deleteInvoice(UUID id);

}
