package org.openmrs.module.tbcase.api.db;

import org.openmrs.annotation.Authorized;
import org.openmrs.module.tbcase.TBCaseReport;

import java.util.List;

public interface TBCaseReportsDAO {

    @Authorized("View Reports")
    public List<TBCaseReport> getTBCaseReports();

    @Authorized("View Reports")
    public TBCaseReport getTBCaseReport(Integer reportId);
}
