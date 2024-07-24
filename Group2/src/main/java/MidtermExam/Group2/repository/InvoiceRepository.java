package MidtermExam.Group2.repository;

import MidtermExam.Group2.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID>, JpaSpecificationExecutor<Invoice> {
    /**
     * Find all invoices with pagination
     * @param pageable pagination information
     * @return page of invoices
     */
    @Query("SELECT i FROM Invoice i JOIN FETCH i.customer JOIN FETCH i.invoiceProducts")
    Page<Invoice> findAll(Pageable pageable);

    @Query("SELECT i FROM Invoice i JOIN FETCH i.customer JOIN FETCH i.invoiceProducts WHERE i.id = :id")
    Page<Invoice> findInvoiceById(UUID id, Pageable pageable);
}
