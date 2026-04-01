package com.librimanga.backendlibrimanga;

import com.librimanga.backendlibrimanga.utenti.UtentiControllerTest;
import com.librimanga.backendlibrimanga.utenti.UtentiServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        UtentiServiceTest.class,
        UtentiControllerTest.class
})

public class SuiteTest {
}
