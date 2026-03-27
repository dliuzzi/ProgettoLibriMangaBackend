package com.librimanga.backendlibrimanga;

import com.librimanga.backendlibrimanga.service.UtentiService;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        UtentiService.class
})

public class SuiteTest {
}
