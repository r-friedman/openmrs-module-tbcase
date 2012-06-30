/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.tbcase;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Role;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.Activator;
import org.openmrs.module.ModuleActivator;
import org.openmrs.util.OpenmrsConstants;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class TBCaseModuleActivator implements ModuleActivator {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	public void contextRefreshed() {
			
	}

	public void started() {
		
	}

	public void stopped() {
		
		
	}

	public void willRefreshContext() {
		// TODO Auto-generated method stub
		
	}

	public void willStart() {
/*		
		Set<Role> inheritedRoleSet=new HashSet<Role>();
		inheritedRoleSet.add(Context.getUserService().getRole(OpenmrsConstants.AUTHENTICATED_ROLE));
		UserService us = Context.getUserService();
		Role humanResourceManager = us.getRole("HR Manager");
		// Create role if it doesn't exist
		if (humanResourceManager == null){
			humanResourceManager=new Role("HR Manager", "HR Role");
			humanResourceManager.setInheritedRoles(inheritedRoleSet);
			us.saveRole(humanResourceManager); 
		}
		Role humanResourceStrongClerk=us.getRole("HR Strong Clerk");
		// Create role if it doesn't exist
		if (humanResourceStrongClerk == null ){
		 humanResourceStrongClerk=new Role("HR Strong Clerk", "HR Role");
		 humanResourceStrongClerk.setInheritedRoles(inheritedRoleSet);
		 us.saveRole(humanResourceStrongClerk);
		 }

        Role humanResourceWeakClerk=us.getRole("HR Weak Clerk");
        // Create role if it doesn't exist
        if (humanResourceWeakClerk == null ){
            humanResourceWeakClerk=new Role("HR Weak Clerk", "HR Role");
            humanResourceWeakClerk.setInheritedRoles(inheritedRoleSet);
            us.saveRole(humanResourceWeakClerk);
        }


        addPrivilegesToRole(humanResourceManager,Arrays.asList("Add Post","Add Assignments","Find Human Resources","View Posts","View Staff Demographics",
                "View Staff", "Manage Job Titles","Manage Posts","Manage Staff","Manage Staff Attribute Types","View Reports","View Concepts","View People","Edit People","Add People"));
		addPrivilegesToRole(humanResourceStrongClerk,Arrays.asList("Add Post","Add Assignments","Find Human Resources","View Posts","View Staff Demographics",
                "View Staff","View Concepts","View People","Edit People","Add People","Manage Posts","View Reports"));
        addPrivilegesToRole(humanResourceWeakClerk,Arrays.asList("Add Assignments","Edit People","Add People","View People","View Concepts","Manage Staff","Find Human Resources",
                "View Staff","View Posts","View Staff Demographics","View Reports"));

        log.info("StartingTB Case Report Resource Module");
*/		
	}

	public void willStop() {
		
	}
	
	public void addPrivilegesToRole(Role role, List<String> privs) {
		UserService us = Context.getUserService();
		for (int i = 0; i < privs.size(); i++) {
			role.addPrivilege(us.getPrivilege(privs.get(i)));
		}
		us.saveRole(role);
	}
	
}

