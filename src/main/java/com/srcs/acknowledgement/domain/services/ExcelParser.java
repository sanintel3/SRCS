package com.srcs.acknowledgement.domain.services;

import com.srcs.acknowledgement.domain.Donation;
import org.apache.poi.ss.usermodel.Cell;
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
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK;

/**
 * Created by santhosh on 09/07/16.
 */
@Component
public class ExcelParser {

    public List<Donation> parseDonations(InputStream statement) throws Exception {

        Sheet statementSheet = new XSSFWorkbook(statement).getSheetAt(0);

        List<Donation> donations = new ArrayList<>();

        for (int rowIndex = 1; rowIndex < statementSheet.getPhysicalNumberOfRows(); rowIndex++) {
            Row currentRow = statementSheet.getRow(rowIndex);
            boolean isBlankRow = currentRow == null || currentRow.getCell(0).getNumericCellValue() == 0;
            if (isBlankRow) {
                break;
            }

            String transactionType = currentRow.getCell(6).getStringCellValue();

            if ("CR".equalsIgnoreCase(transactionType)) {
                Date date = currentRow.getCell(2).getDateCellValue();
                double amount = currentRow.getCell(7).getNumericCellValue();
                String email = findEmail(currentRow);
                if(isNotEmpty(email)){
                    donations.add(new Donation(newEmail(email), newMoney(amount), date));
                }
            }
        }

        return donations;
    }

    private String findEmail(Row currentRow) {
        Cell emailCell = currentRow.getCell(9);
        return emailCell.getCellType() == CELL_TYPE_BLANK ? "" : emailCell.getStringCellValue();
    }
}
