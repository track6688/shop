<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:iterator var="orderItem" value="oiList">


	<table	border="0" width="100%">
		<tr>
			<td><img width="40" height="45" src="${ pageContext.request.contextPath }/<s:property value="#orderItem.product.image"/>"></td>
		<td><s:property value="#orderItem.product.pname"/></td>
		<td><s:property value="#orderItem.count"/></td>
		<td><s:property value="#orderItem.subtotal"/></td>
		</tr>
	</table>
	
</s:iterator>