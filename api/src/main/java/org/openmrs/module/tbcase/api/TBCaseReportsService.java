package org.openmrs.module.tbcase.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.tbcase.TBCaseReport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TBCaseReportsService {

    @Authorized("View Reports")
    public List<TBCaseReport> getTBCaseReports() throws DAOException;

    @Authorized("View Reports")
	public TBCaseReport getTBCaseReport(Integer reportId) throws DAOException;
}
