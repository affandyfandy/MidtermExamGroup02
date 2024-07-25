package MidtermExam.Group2.service.impl;

import MidtermExam.Group2.entity.Invoice;
import MidtermExam.Group2.entity.InvoiceProduct;
import MidtermExam.Group2.repository.InvoiceRepository;
import MidtermExam.Group2.service.ExportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class ExportServiceImpl implements ExportService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public ExportServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public ByteArrayInputStream exportInvoicesToExcel(UUID customerId, Integer month, Integer year) throws IOException {
        List<Invoice> invoices = invoiceRepository.findByCustomerAndDate(customerId, month, year);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Invoices");

            sheet.setColumnWidth(1, 40 * 256);

            int rowNum = 0;

            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 22);

            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setFont(titleFont);

            Row titleRow = sheet.createRow(rowNum++);

            sheet.addMergedRegion(new CellRangeAddress(
                    0, // start row
                    0, // end row
                    1, // start column
                    3  // end column
            ));

            Cell titleCell = titleRow.createCell(1);
            titleCell.setCellValue("Invoices");
            titleCell.setCellStyle(titleStyle);

            Row groupNameRow = sheet.createRow(rowNum++);
            groupNameRow.createCell(1).setCellValue("Group 2");

            if (invoices.isEmpty()) {
                Row emptyRow = sheet.createRow(rowNum++);
                emptyRow.createCell(1).setCellValue("No invoices found");
            }

            for (Invoice invoice : invoices) {

                rowNum++;

                Row topBorderLine = sheet.createRow(rowNum++);
                for (int i = 1; i <= 5; i++) {
                    Cell cell = topBorderLine.createCell(i);
                    cell.setCellStyle(sheet.getWorkbook().createCellStyle());
                    cell.getCellStyle().setBorderTop(BorderStyle.THIN);
                    if (i == 1) {
                        cell.getCellStyle().setBorderLeft(BorderStyle.THIN);
                    }
                    if (i == 5) {
                        cell.getCellStyle().setBorderRight(BorderStyle.THIN);
                    }
                }

                rowNum = createInvoiceSection(sheet, invoice, rowNum);

                Row bottomBorderLine = sheet.createRow(rowNum++);
                for (int i = 1; i <= 5; i++) {
                    Cell cell = bottomBorderLine.createCell(i);
                    cell.setCellStyle(sheet.getWorkbook().createCellStyle());
                    cell.getCellStyle().setBorderBottom(BorderStyle.THIN);
                    if (i == 1) {
                        cell.getCellStyle().setBorderLeft(BorderStyle.THIN);
                    }
                    if (i == 5) {
                        cell.getCellStyle().setBorderRight(BorderStyle.THIN);
                    }
                }
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    private int createInvoiceSection(Sheet sheet, Invoice invoice, int rowNum) {
        int startRow = rowNum;

        // Create a DataFormat for the workbook and CellStyle with the currency format
        DataFormat format = sheet.getWorkbook().createDataFormat();
        CellStyle currencyStyle = sheet.getWorkbook().createCellStyle();
        currencyStyle.setDataFormat(format.getFormat("$#,##0.00"));

        CellStyle topBorderStyle = sheet.getWorkbook().createCellStyle();
        CellStyle bottomBorderStyle = sheet.getWorkbook().createCellStyle();
        CellStyle leftBorderStyle = sheet.getWorkbook().createCellStyle();
        CellStyle rightBorderStyle = sheet.getWorkbook().createCellStyle();

        topBorderStyle.setBorderTop(BorderStyle.THIN);
        bottomBorderStyle.setBorderBottom(BorderStyle.THIN);
        leftBorderStyle.setBorderLeft(BorderStyle.THIN);
        rightBorderStyle.setBorderRight(BorderStyle.THIN);

        Row invoiceIdRow = sheet.createRow(rowNum++);
        invoiceIdRow.createCell(1).setCellValue("Invoice ID");
        invoiceIdRow.createCell(2).setCellValue(invoice.getId().toString());

        Row invoiceDateRow = sheet.createRow(rowNum++);
        invoiceDateRow.createCell(1).setCellValue("Invoice Date");
        invoiceDateRow.createCell(2).setCellValue(invoice.getInvoiceDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm:ss")));

        Row invoiceAmountRow = sheet.createRow(rowNum++);
        invoiceAmountRow.createCell(1).setCellValue("Invoice Amount");

        Cell invoiceAmountCell = invoiceAmountRow.createCell(2);
        invoiceAmountCell.setCellStyle(currencyStyle);
        invoiceAmountCell.setCellValue(invoice.getInvoiceAmount().doubleValue());

        rowNum++;

        Row customerIdRow = sheet.createRow(rowNum++);
        customerIdRow.createCell(1).setCellValue("Customer ID");
        customerIdRow.createCell(2).setCellValue(invoice.getCustomer().getId().toString());

        Row customerNameRow = sheet.createRow(rowNum++);
        customerNameRow.createCell(1).setCellValue("Customer Name");
        customerNameRow.createCell(2).setCellValue(invoice.getCustomer().getName());

        rowNum++;

        Row listTitleRow = sheet.createRow(rowNum++);
        listTitleRow.createCell(1).setCellValue("List Products");

        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(1).setCellValue("ID");
        headerRow.createCell(2).setCellValue("Name");
        headerRow.createCell(3).setCellValue("Price");
        headerRow.createCell(4).setCellValue("Quantity");
        headerRow.createCell(5).setCellValue("Amount");

        for (InvoiceProduct product : invoice.getInvoiceProducts()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(1).setCellValue(product.getProduct().getId().toString());
            row.createCell(2).setCellValue(product.getProduct().getName());

            Cell priceCell = row.createCell(3);
            priceCell.setCellStyle(currencyStyle);
            priceCell.setCellValue(product.getProduct().getPrice().doubleValue());

            row.createCell(4).setCellValue(product.getQuantity());

            Cell amountCell = row.createCell(5);
            amountCell.setCellStyle(currencyStyle);
            amountCell.setCellValue(product.getAmount().doubleValue());
        }

        for (int rowIndex = startRow; rowIndex < rowNum; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            for (int colIndex = 1; colIndex <= 5; colIndex++) {
                Cell cell = row.getCell(colIndex);
                if (cell == null) {
                    cell = row.createCell(colIndex);
                }

                if (colIndex == 1) {
                    cell.setCellStyle(leftBorderStyle);
                } else if (colIndex == 5) {
                    cell.setCellStyle(rightBorderStyle);
                }
            }
        }

        return rowNum;
    }
}
