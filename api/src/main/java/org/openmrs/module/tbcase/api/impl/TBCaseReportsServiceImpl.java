package org.openmrs.module.tbcase.api.impl;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.tbcase.TBCaseReport;
import org.openmrs.module.tbcase.api.TBCaseReportsService;
import org.openmrs.module.tbcase.api.db.TBCaseReportsDAO;

import java.util.List;

public class TBCaseReportsServiceImpl implements TBCaseReportsService {

    private TBCaseReportsDAO dao;

	public void setDao(TBCaseReportsDAO dao)
	{
		this.dao = dao;
	}

    public List<TBCaseReport> getTBCaseReports() throws DAOException {
		return dao.getTBCaseReports();
	}
	public TBCaseReport getTBCaseReport(Integer reportId) throws DAOException{
		return dao.getTBCaseReport(reportId);
	}
}
