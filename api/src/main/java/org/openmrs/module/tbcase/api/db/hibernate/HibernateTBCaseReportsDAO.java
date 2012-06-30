package org.openmrs.module.tbcase.api.db.hibernate;

import org.hibernate.SessionFactory;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.tbcase.TBCaseReport;
import org.openmrs.module.tbcase.api.db.TBCaseReportsDAO;

import java.util.List;

public class HibernateTBCaseReportsDAO implements TBCaseReportsDAO{

    private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    public TBCaseReport getTBCaseReport(Integer reportId) throws DAOException {
		TBCaseReport tbcaseReport = (TBCaseReport) sessionFactory.getCurrentSession().get(TBCaseReport.class, reportId);
		if(tbcaseReport!=null)
		tbcaseReport.initParamsFromLoad();
		return tbcaseReport;
	}

	public List<TBCaseReport> getTBCaseReports() throws DAOException {
		List<TBCaseReport> reports = sessionFactory.getCurrentSession().createCriteria(TBCaseReport.class).list();
		if(reports!=null){
		for (TBCaseReport tbcaseReport : reports) {
			tbcaseReport.initParamsFromLoad();
		}
		}
		return reports;
	}
}
