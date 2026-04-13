package com.librimanga.backendlibrimanga;

import com.librimanga.backendlibrimanga.autori.AutoriControllerTest;
import com.librimanga.backendlibrimanga.caseEditrici.CaseEditriciControllerTest;
import com.librimanga.backendlibrimanga.categorie.CategorieControllerTest;
import com.librimanga.backendlibrimanga.libri.LibriControllerTest;
import com.librimanga.backendlibrimanga.ordini.OrdiniControllerTest;
import com.librimanga.backendlibrimanga.utenti.UtentiControllerTest;
import com.librimanga.backendlibrimanga.utenti.UtentiServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        UtentiServiceTest.class,
        UtentiControllerTest.class,
        AutoriControllerTest.class,
        CaseEditriciControllerTest.class,
        CategorieControllerTest.class,
        LibriControllerTest.class,
        OrdiniControllerTest.class,
})

public class SuiteTest {
}
