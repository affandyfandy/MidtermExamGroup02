package MidtermExam.Group2.repository;

import MidtermExam.Group2.entity.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, UUID> {
}
