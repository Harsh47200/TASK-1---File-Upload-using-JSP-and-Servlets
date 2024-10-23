<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/result.css">
</head>
<body>
<div class="upload-container">
	 <h2>File Uploaded Successfully!</h2>
    <p>File Name: ${fileName}</p>
    <p>File Size: ${fileSize} bytes</p>
    <p>File Type: ${fileType}</p>
    <a href="upload.jsp">Upload Another File</a>
	</div>
</body>
</html>