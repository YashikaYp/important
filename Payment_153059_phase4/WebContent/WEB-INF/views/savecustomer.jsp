<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Account</title>
<style >
.error{

color:red;
font_weight:bold;
}
</style>
</head>
<body>
<div align="center">
<h2>Create Account</h2>
<form:form action="save" method="post" modelAttribute="customer">
<table align="center">
<tr>
<td>Name:</td>
<td><form:input path="name" size="30"/></td>
<td><form:errors path="name" cssClass="error"/></td>
</tr>
<br>
<tr>
<td>Mobile Number:</td>
<td><form:input path="mobileNo" size="30"/></td>
<td><form:errors path="mobileNo" cssClass="error"/></td>
</tr>
<br>
<tr>
<td>Initial Balance:</td>
<td><form:input path="wallet.balance" size="30"/></td>
<td><form:errors path="wallet.balance" cssClass="error"/></td>
</tr>
<br>
<tr>
<td><input type="submit" value="Submit"/></td>
</tr>
</table>
</form:form>
</div>
</body>
</html>