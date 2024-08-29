# POS - Point of Sale System

## Overview
This POS system is a comprehensive solution for managing invoices, products, and customers. Built using `Spring Boot` and `MySQL`, it features a `RESTful API` architecture with Swagger documentation, pagination, validation, and time-stamped entities.

## Features

1. **Common Requirements**
   - All APIs support pagination.
   - RESTful API design follows best practices.
   - Parameter validation.
   - Entities include `created_time` and `updated_time`.
   - API documentation via Swagger.

2. **Invoice Management**
   - **Invoice List**
     - Search by customer name using a wildcard search (`like %input%`).
     - Filter by customer ID, date, or month.
     - Sort invoices by date or amount.
     - Fields: `id`, `invoice amount`, `customer name`, `invoice date`.
   - **Add New Invoice**
     - Select customer.
     - Add new invoice.
     - Add products to the invoice and adjust quantities.
     - Validate products are configured and active.
   - **Show Invoice Detail**
     - Displays customer details (`id`, `name`).
     - Invoice details (`id`, `invoice amount`, `invoice date`).
     - List of products with quantity, price, and total amount.
   - **Export PDF**
     - Export detailed invoice information to PDF.
   - **Edit Invoice**
     - Editable within 10 minutes from creation.
     - Similar to adding a new invoice.

3. **Product Management**
   - List products with fields: `id`, `name`, `price`, `status`.
   - Search products by name or status, sort by name or price.
   - Add, edit, activate/deactivate products.
   - Import products from an Excel file.

4. **Customer Management**
   - List customers with fields: `id`, `name`, `phone number`.
   - Add new customers.
   - Edit customer details.
   - Activate/deactivate customers.

5. **Reports (Optional)**
   - Generate revenue reports by day, month, or year.

6. **Excel Export (Optional)**
   - Export list of invoices to Excel based on filters: customer, month, year.
   - Include details: `invoice id`, `customer id`, `customer name`, `amount`, list of products (`id`, `name`, `price`, `quantity`, `amount`).

## Common Rules
- **Main Tasks**
  - Design business flow, database, and API documentation.
- **Git Workflow**
  - Establish code structure on `main`/`develop` branch initially.
  - Branch out feature branches from `develop` (naming: `feature/[description]`).
  - Create PRs, perform peer reviews, and include trainers for review before merging.

## Technologies Used
- Spring Boot
- MySQL
- Swagger for API Documentation

## Installation
1. Clone the repository.
2. Set up the `MySQL` database and update the connection settings in `application.properties`.
3. Build and run the project using Maven.

## Contributing
1. Fork the repository.
2. Create a new branch (`feature/[description]`).
3. Make your changes and commit them.
4. Push to the branch and open a pull request.

## License
This project is licensed under the MIT License.

## Contact
For further inquiries or issues, please contact.
