<ul id="menu">
	<li class="first">
		<a href="${pageContext.request.contextPath}/admin"><spring:message code="admin.title.short"/></a>
	</li>
		<openmrs:hasPrivilege privilege="View Reports">
		<li <c:if test='<%= request.getRequestURI().contains("eport") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/tbcase/admin/reportSelection.list">
				<spring:message code="Reports"/>
			</a>
		</li>
		</openmrs:hasPrivilege>
</ul>
