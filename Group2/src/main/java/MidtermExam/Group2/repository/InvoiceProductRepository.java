package MidtermExam.Group2.repository;

import MidtermExam.Group2.entity.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.UUID;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, UUID> {
    @Query("SELECT SUM(ip.amount) FROM InvoiceProduct ip WHERE ip.invoice.id = :invoiceId")
    BigDecimal calculateTotalAmountByInvoiceId(UUID invoiceId);
}
