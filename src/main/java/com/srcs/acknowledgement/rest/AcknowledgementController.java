package com.srcs.acknowledgement.rest;

import com.srcs.acknowledgement.domain.services.AcknowledgementProcessor;
import com.srcs.rest.BaseController;
import com.srcs.acknowledgement.rest.api.AcknowledgementStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public List<AcknowledgementStatus> sendAck() throws Exception {
        return acknowledgementProcessor.process().stream().map(responseMapper::map).collect(toList());
    }
}
