package MidtermExam.Group2.mapper;

import MidtermExam.Group2.dto.InvoiceProductDTO;
import MidtermExam.Group2.dto.InvoiceProductWithoutIdDTO;
import MidtermExam.Group2.entity.Invoice;
import MidtermExam.Group2.entity.InvoiceProduct;
import MidtermExam.Group2.entity.Product;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { InvoiceMapper.class, ProductMapper.class })
public interface InvoiceProductMapper {
        @Mappings({
                        @Mapping(source = "invoice.id", target = "invoiceId"),
                        @Mapping(source = "product.id", target = "productId"),
                        @Mapping(source = "product.name", target = "productName"),
        })
        InvoiceProductDTO toInvoiceProductDTO(InvoiceProduct invoiceProduct);

        @Mappings({
                        @Mapping(source = "invoiceId", target = "invoice.id"),
                        @Mapping(source = "productId", target = "product.id"),
                        @Mapping(source = "productName", target = "product.name"),
        })
        InvoiceProduct toInvoiceProduct(InvoiceProductDTO invoiceProductDTO, @Context Invoice invoice,
                        @Context Product product);

        @Mappings({
                        @Mapping(source = "invoice.id", target = "invoiceId"),
                        @Mapping(source = "product.name", target = "productName"),
                        @Mapping(source = "quantity", target = "quantity"),
                        @Mapping(source = "amount", target = "amount")
        })
        InvoiceProductWithoutIdDTO toInvoiceProductWithoutIdDTO(InvoiceProduct invoiceProduct);

        @Mappings({
                        @Mapping(source = "invoiceId", target = "invoice.id"),
                        @Mapping(source = "productName", target = "product.name"),
                        @Mapping(source = "quantity", target = "quantity"),
                        @Mapping(source = "amount", target = "amount")
        })
        InvoiceProduct toInvoiceProduct(InvoiceProductWithoutIdDTO invoiceProductWithoutIdDTO, @Context Invoice invoice,
                        @Context Product product);
}