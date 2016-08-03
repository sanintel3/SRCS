package com.srcs.acknowledgement.domain.services;

import org.junit.Test;

/**
 * Created by santhosh on 03/08/16.
 */
public class ExcelParserTest {

    @Test
    public void sanityTest() throws Exception {
        System.out.println(new ExcelParser().parseDonations().size());
    }
}