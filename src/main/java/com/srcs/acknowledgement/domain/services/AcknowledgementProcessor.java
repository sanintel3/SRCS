package com.srcs.acknowledgement.domain.services;

import com.srcs.acknowledgement.domain.EmailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by santhosh on 09/07/16.
 */
@Service
public class AcknowledgementProcessor {

    private final ExcelParser excelParser;
    private final EmailSender emailSender;

    @Autowired
    public AcknowledgementProcessor(ExcelParser excelParser, EmailSender emailSender) {
        this.excelParser = excelParser;
        this.emailSender = emailSender;
    }

    public List<EmailStatus> process(InputStream statementContent) throws Exception {
        return excelParser.parseDonations(statementContent).stream().map(emailSender::send).collect(toList());
    }
}
