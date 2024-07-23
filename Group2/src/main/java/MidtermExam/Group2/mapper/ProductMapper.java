package MidtermExam.Group2.mapper;

import MidtermExam.Group2.dto.ProductDTO;
import MidtermExam.Group2.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);
}
