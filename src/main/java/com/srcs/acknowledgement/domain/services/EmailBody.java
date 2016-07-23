package com.srcs.acknowledgement.domain.services;

import com.srcs.acknowledgement.domain.Donation;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.time.DateFormatUtils.format;

/**
 * Created by santhosh on 23/07/16.
 */
@Component
public class EmailBody {

    private final Configuration configuration;

    @Autowired
    public EmailBody(@Qualifier("freemarkerConfiguration") Configuration configuration) {
        this.configuration = configuration;
    }

    public String content(Donation donation) throws IOException, TemplateException {
        StringWriter bodyContent = new StringWriter();

        Template template = configuration.getTemplate("acknowledgement.ftl");
        template.process(getPlaceHolderData(donation), bodyContent);

        return bodyContent.toString();
    }

    private Map<String, String> getPlaceHolderData(Donation donation){
        Map<String, String> data = new HashMap<>();
        data.put("amount", donation.amount().formattedValue());
        data.put("date", format(donation.date(), "dd-MMM-yyyy"));
        return data;
    }
}
