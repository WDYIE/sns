<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>�ɹ�ҳ��</title>
</head>
<body>
	��ת���ɹ�ҳ��
	<br>
	<img src="getImage?id=1">
	<s:debug></s:debug>
</body>
</html>
