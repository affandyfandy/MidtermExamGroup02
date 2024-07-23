package MidtermExam.Group2.mapper;

import MidtermExam.Group2.dto.InvoiceProductDTO;
import MidtermExam.Group2.entity.Invoice;
import MidtermExam.Group2.entity.InvoiceProduct;
import MidtermExam.Group2.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {InvoiceMapper.class, ProductMapper.class})
public interface InvoiceProductMapper {
    @Mappings({
            @Mapping(source = "invoice.id", target = "invoiceId"),
            @Mapping(source = "product.id", target = "productId"),
    })
    InvoiceProductDTO toInvoiceProductDTO(InvoiceProduct invoiceProduct);

    @Mappings({
            @Mapping(source = "invoiceId", target = "invoice.id"),
            @Mapping(source = "productId", target = "product.id"),
    })
    InvoiceProduct toInvoiceProduct(InvoiceProductDTO invoiceProductDTO, @Context Invoice invoice, @Context Product product);
}
