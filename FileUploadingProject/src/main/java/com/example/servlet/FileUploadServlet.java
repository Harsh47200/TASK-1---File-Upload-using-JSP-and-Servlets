package com.example.servlet;



import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/uploadFile")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 15    // 15 MB
)
public class FileUploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Directory to save uploaded files
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        System.out.println("uploadpath=====>>>>>" + uploadPath);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
		System.out.println("upload dir"+uploadDir);
		if (!uploadDir.exists() || !uploadDir.isDirectory()) {
		    System.out.println("Failed to create upload directory.");
		    request.setAttribute("errorMessage", "Failed to create upload directory.");
		    request.getRequestDispatcher("uploadError.jsp").forward(request, response);
		    return;
		}
        String fileName = "";
        String fileType = "";
        long fileSize = 0;
        String errorMessage = null;

        try {
            // Retrieves the file part
            Part filePart = request.getPart("file");

            if (filePart != null && filePart.getSize() > 0) {
                // Get file name
                fileName = filePart.getSubmittedFileName();
                // Get file type
                fileType = filePart.getContentType();
                // Get file size
                fileSize = filePart.getSize();

                // Check for valid file type (e.g., image/png or text/plain)
                if (!fileType.equals("image/png") && !fileType.equals("text/plain")) {
                    throw new ServletException("Invalid file type. Only PNG and TXT files are allowed.");
                }

                // Save the file on the server
                filePart.write(uploadPath + File.separator + fileName);
                System.out.println("File saved successfully: " + fileName);

                // Forward the details to a JSP page
                request.setAttribute("fileName", fileName);
                request.setAttribute("fileSize", fileSize);
                request.setAttribute("fileType", fileType);
                request.getRequestDispatcher("result.jsp").forward(request, response);
            } else {
                errorMessage = "No file selected!";
            }

        } catch (IOException | ServletException e) {
            errorMessage = e.getMessage();
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("uploadError.jsp").forward(request, response);
        }
    }
}
