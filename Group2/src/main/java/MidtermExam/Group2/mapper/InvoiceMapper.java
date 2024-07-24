package MidtermExam.Group2.mapper;

import MidtermExam.Group2.dto.InvoiceDetailDTO;
import MidtermExam.Group2.dto.InvoiceListDTO;
import MidtermExam.Group2.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { CustomerMapper.class, InvoiceProductMapper.class })
public interface InvoiceMapper {

    @Mapping(source = "customer.name", target = "customerName")
    InvoiceListDTO toInvoiceListDTO(Invoice invoice);

    // Invoice Detail Mapper here
    // to do

    Invoice toInvoice(InvoiceListDTO invoiceDTO);

    @Mapping(source = "invoice.id", target = "invoiceId")
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "invoiceProducts", target = "products")
    InvoiceDetailDTO toInvoiceDetailDTO(Invoice invoice);

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "products", target = "invoiceProducts")
    Invoice toInvoice(InvoiceDetailDTO invoiceDetailDTO);
}
