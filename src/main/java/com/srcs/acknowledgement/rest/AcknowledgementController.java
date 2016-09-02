package com.srcs.acknowledgement.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srcs.acknowledgement.domain.services.AcknowledgementProcessor;
import com.srcs.rest.BaseController;
import com.srcs.acknowledgement.rest.api.AcknowledgementStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by santhosh on 23/07/16.
 */
@Controller
public class AcknowledgementController extends BaseController {

    @Autowired
    private AcknowledgementProcessor acknowledgementProcessor;

    @Autowired
    private ResponseMapper responseMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/sendAck")
    public String uploadForm() throws Exception {
        return "uploadForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    @ResponseBody
    public List<AcknowledgementStatus> submitForm(@RequestParam("file") MultipartFile file, final RedirectAttributes redirectAttributes) throws Exception {
        return acknowledgementProcessor.process(file.getInputStream()).stream().map(responseMapper::map).collect(toList());
    }
}
