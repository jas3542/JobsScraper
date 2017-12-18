<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="tags" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="Jobs.css" type="text/css">
	<title>JobScraper</title>
</head>
<body>

<!-- TODO: create different jsp for each webSite -->

	<tags:iterator value="jobList">
		<tags:if test="#webSite == Monster">
			<div class="webSite">
				<tags:property value="webSite"/>
			</div>
		</tags:if>
		
		<tags:elseif test="#webSite == Indeed">
			<div class="WebSite">
				<tags:property value="webSite"/>
			</div>
		</tags:elseif>
		
		<div class="listJobs">
			<div class="nameJob">
				<tags:property value="name" /> ||
			</div>
			<div class="companyJob">
				<tags:property value="company" /> ||
			</div>
			<div class="locationJob">
				<tags:property value="location" /> ||
			</div>
			<div class="detailsLinkJob">
				<tags:a target="_blank" href="%{pageLink}">Link</tags:a> ||
			</div>
			<tags:if test="summary != ''">
				<div class="summaryJob">
					<tags:property value="summary" /> ||
				</div>
			</tags:if>
			<br>
		</div>
	</tags:iterator>
	<tags:property value="msg" />

	<tags:form action="doScraping">
		<tags:submit name="Click For Action" />
	</tags:form>
</body>
</html>