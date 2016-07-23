package com.srcs.acknowledgement.domain.services;

import com.srcs.acknowledgement.domain.Donation;
import com.srcs.config.SrcsConfig;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Date;

import static com.srcs.datatypes.Email.newEmail;
import static com.srcs.datatypes.Money.newMoney;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by santhosh on 23/07/16.
 */
public class EmailBodyTest {

    @Test
    public void testContent() throws Exception {
        Date date = DateUtils.parseDate("25-May-2014", "dd-MMM-yyyy");
        Donation donation = new Donation(newEmail("s@gmail.com"), newMoney(100.05), date);

        EmailBody emailBody = new EmailBody(new SrcsConfig().freemarkerConfiguration());
        String emailBodyContent = emailBody.content(donation);

        assertThat(emailBodyContent, containsString("25-May-2014"));
        assertThat(emailBodyContent, containsString("INR 100.05"));
    }
}