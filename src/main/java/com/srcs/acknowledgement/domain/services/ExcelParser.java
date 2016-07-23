package com.srcs.acknowledgement.domain.services;

import com.srcs.acknowledgement.domain.Donation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.srcs.datatypes.Email.newEmail;
import static com.srcs.datatypes.Money.newMoney;

/**
 * Created by santhosh on 09/07/16.
 */
@Component
public class ExcelParser {

    public List<Donation> parseDonations() throws Exception {

        InputStream statement = this.getClass().getClassLoader().getResourceAsStream("monthlyStatement.xls");
        Sheet statementSheet = new XSSFWorkbook(statement).getSheetAt(0);

        List<Donation> donations = new ArrayList<>();

        for (int rowIndex = 1; rowIndex < statementSheet.getPhysicalNumberOfRows(); rowIndex++) {
            Row currentRow = statementSheet.getRow(rowIndex);
            if(currentRow.getCell(0).getNumericCellValue() == 0){
                break;
            }

            Date date = currentRow.getCell(2).getDateCellValue();
            String transactionType = currentRow.getCell(6).getStringCellValue();
            double amount = currentRow.getCell(7).getNumericCellValue();
            String email = currentRow.getCell(9).getStringCellValue();

            if ("CR".equalsIgnoreCase(transactionType)) {
                donations.add(new Donation(newEmail(email), newMoney(amount), date));
            }
        }

        return donations;
    }
}
