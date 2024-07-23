package MidtermExam.Group2.mapper;

import MidtermExam.Group2.dto.InvoiceListDTO;
import MidtermExam.Group2.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface InvoiceMapper {

    @Mapping(source = "customer.name", target = "customerName")
    InvoiceListDTO toInvoiceListDTO(Invoice invoice);

    // Invoice Detail Mapper here
    // to do

    Invoice toInvoice(InvoiceListDTO invoiceDTO);
}
