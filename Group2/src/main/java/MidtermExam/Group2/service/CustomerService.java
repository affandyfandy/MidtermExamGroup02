package MidtermExam.Group2.service;

import MidtermExam.Group2.dto.CustomerDTO;
import MidtermExam.Group2.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
}
