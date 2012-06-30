package org.openmrs.module.tbcase.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.tbcase.api.TBCaseReportsService;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.SkipBaseSetup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TBCaseReportsServiceTest extends BaseModuleContextSensitiveTest {
    TBCaseReportsService tbcaseReportsService;

    @Before
	public void before() throws Exception {
        executeDataSet("person_test_data.xml");
        executeDataSet("privilege_data.xml");
		executeDataSet("reports_service_test_data.xml");
        tbcaseReportsService = Context.getService(TBCaseReportsService.class);
	}

    @Test
    @SkipBaseSetup
    public void shouldSetUpContext(){
        assertNotNull(Context.getService(TBCaseReportsService.class));
    }

    @Test
    @SkipBaseSetup
    public void shouldGetReportById(){
        Context.authenticate("hrmanager","Hrmanager123");
        assertNotNull("should get report by ID",tbcaseReportsService.getTBCaseReport(1));
    }

    @Test
    @SkipBaseSetup
    public void shouldGetAllReports(){
        Context.authenticate("hrmanager","Hrmanager123");
        assertEquals(2,tbcaseReportsService.getTBCaseReports().size());

    }
}
