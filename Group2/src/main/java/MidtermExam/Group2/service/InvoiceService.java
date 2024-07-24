package MidtermExam.Group2.service;

import MidtermExam.Group2.criteria.InvoiceSearchCriteria;
import MidtermExam.Group2.dto.CustomerDTO;
import MidtermExam.Group2.dto.InvoiceListDTO;
import MidtermExam.Group2.dto.InvoicesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {
    Page<InvoiceListDTO> getAllInvoices(Pageable pageable, InvoiceSearchCriteria criteria);
    InvoicesDTO addInvoice(InvoicesDTO invoicesDTO);
}
