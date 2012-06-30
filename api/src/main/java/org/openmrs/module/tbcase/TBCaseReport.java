package org.openmrs.module.tbcase;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;




public class TBCaseReport  implements java.io.Serializable {


   
	private static final long serialVersionUID = 1L;
	  

     private int reportId;
     private String name;
     private String description;
     private String fileName;
     private List<TBCaseParameter> parameters=new ArrayList<TBCaseParameter>();

   

   
    public TBCaseReport() {
    }
    
    
    public TBCaseReport(int reportId, String name, String description, String fileName) {
       
        this.name = name;
        this.description = description;
        this.fileName = fileName;
    }
    

    @ElementList(required = false)
    public List<TBCaseParameter> getParameters() {
		return this.parameters;
	}
    @ElementList(required = false)
	public void setParameters(List<TBCaseParameter> parameters) {
		this.parameters = parameters;
	}

    public int getReportId() {
        return this.reportId;
    }
    
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
   
    public void initParamsFromLoad() {
		for (TBCaseParameter param : parameters) {
			if(param!=null)
			param.initFromLoad();
		}
	}







}
