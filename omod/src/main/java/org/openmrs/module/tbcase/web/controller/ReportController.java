package org.openmrs.module.tbcase.web.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.tbcase.ReportGenerator;
import org.openmrs.module.tbcase.TBCaseParameter;
import org.openmrs.module.tbcase.TBCaseReport;
import org.openmrs.module.tbcase.api.TBCaseReportsService;
import org.openmrs.propertyeditor.ConceptEditor;
import org.openmrs.propertyeditor.LocationEditor;
import org.openmrs.util.OpenmrsUtil;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportController {

	private static Log log = LogFactory.getLog(ReportController.class);
	private final String SUCCESS_LIST_VIEW = "/module/tbcase/admin/reportSelection";
	private final String SUCCESS_FORM_VIEW = "/module/tbcase/admin/generateReport";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
		binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
		binder.registerCustomEditor(org.openmrs.Concept.class, new ConceptEditor());
		binder.registerCustomEditor(Location.class, new LocationEditor());
		binder.registerCustomEditor(java.lang.Boolean.class, new CustomBooleanEditor(false));
	}
	
	@RequestMapping(value = "module/tbcase/admin/reportSelection.list",method=RequestMethod.GET)
	public String showList(ModelMap model){
		TBCaseReportsService tbcaseReportsService=Context.getService(TBCaseReportsService.class);
		model.addAttribute("ReportList", tbcaseReportsService.getTBCaseReports());
		return SUCCESS_LIST_VIEW;
	}
	
	@RequestMapping(value = "module/tbcase/admin/generateReport.form",method=RequestMethod.GET)
	@ModelAttribute("TBCaseReport")
	public TBCaseReport showForm(ModelMap model,@RequestParam(required=false,value="reportId") Integer reportId){
		TBCaseReportsService tbcaseReportsService=Context.getService(TBCaseReportsService.class);
		if(reportId!=null)
		return tbcaseReportsService.getTBCaseReport(reportId);
		else
			return new TBCaseReport();
	}
	@RequestMapping(value = "module/tbcase/admin/generateReport.form",method=RequestMethod.POST)
	public String onSubmit(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("TBCaseReport") TBCaseReport report,BindingResult errors) throws IOException
	{
		TBCaseReportsService tbcaseReportsService=Context.getService(TBCaseReportsService.class);
		String outputFormat=request.getParameter("outputFormat");
		TBCaseReport tbcaseReport=tbcaseReportsService.getTBCaseReport(report.getReportId());
		int i=0;
		List<TBCaseParameter> parameterList=report.getParameters();
		for(TBCaseParameter p:parameterList)
		{
			if(p!=null)
				if(parameterList.get(i)!=null && p.getValue()!=null){
					p.setValueClass(tbcaseReport.getParameters().get(i).getValueClass());
					p.setMappedClass(tbcaseReport.getParameters().get(i).getValueClass());
					p.setName(tbcaseReport.getParameters().get(i).getName());
				}
			i++;
			
		}
		tbcaseReport.setParameters(parameterList);
		String jrxmlUrl=request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath())+"/moduleResources/tbcase/jrxmls/"+tbcaseReport.getFileName();
		File generatedReport=ReportGenerator.generate(tbcaseReport, outputFormat, jrxmlUrl);
		FileInputStream fis=new FileInputStream(generatedReport);
        response.addHeader("Content-Disposition","attachment; filename="+tbcaseReport.getName() );
        if(outputFormat.equals("PDF"))
        response.setContentType("application/pdf");
        else if(outputFormat.equals("Excel"))
        response.setContentType("application/vnd.ms-excel");
        response.setContentLength( (int) generatedReport.length() );
        OpenmrsUtil.copyFile(fis, response.getOutputStream());
        if(generatedReport.exists())
        generatedReport.delete();
        File f;
        if((f=new File(OpenmrsUtil.getApplicationDataDirectory()+tbcaseReport.getFileName())).exists())
        f.delete();
		return SUCCESS_FORM_VIEW;
	}
	
}
