package MidtermExam.Group2.mapper;

import MidtermExam.Group2.dto.InvoiceDTO;
import MidtermExam.Group2.dto.InvoiceListDTO;
import MidtermExam.Group2.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface InvoiceMapper {

    @Mapping(source = "customer.name", target = "customerName")
    InvoiceListDTO toInvoiceListDTO(Invoice invoice);

    // Invoice Detail Mapper here
    // to do

    Invoice toInvoice(InvoiceListDTO invoiceDTO);

    // For invoice
    @Mapping(source = "customer.id", target = "customerId")
    InvoiceDTO toInvoicesDTO(Invoice invoice);

    // Invoice Detail Mapper here
    // to do

    Invoice toInvoices(InvoiceDTO invoiceDTO);
}
