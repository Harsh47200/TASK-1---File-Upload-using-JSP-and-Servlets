<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>File Upload</title>
    <link rel="stylesheet" type="text/css" href="css/upload.css">
</head>
<body>

<div class="upload-container">
    <h2>Upload a File</h2>
    <form action="uploadFile" method="post" enctype="multipart/form-data">
        <label for="file">Select a file:</label><br>
        <input type="file" name="file" id="file" accept=".png,.txt" required><br><br>
        <input type="submit" value="Uploads">
    </form>
</div>

</body>

</html>