package com.example;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() {
         System.out.println("HelloServlet initialized");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // GET param file, e.g. http://localhost:8080/demo/hello-servlet?file=image/myfile.jpg
        String filename = request.getParameter("file");

        if (filename != null && !filename.isEmpty()) {
            // Handle the file parameter
            if (filename.startsWith("/"))
                filename = filename.substring(1);

            try {
                // Get the real file system path of the web application's root directory
                String basePath = getServletContext().getRealPath("/");
                // Create a File object representing the requested file by combining base path and filename
                File file = new File(basePath, filename);
                // Open a FileInputStream to read the binary content of the file from disk
                FileInputStream inputFile = new FileInputStream(file);
                // Get the total size of the file in bytes to know how much data to read
                int byteCount = (int) file.length();
                // Create a byte array with size equal to the file length to store the file content in memory
                byte[] fileInBytes = new byte[byteCount];
                // Read all bytes from the file into the byte array
                inputFile.read(fileInBytes);
                // Close the FileInputStream to release system resources and avoid memory leaks
                inputFile.close();
                // Set the response content type to image/png so the browser renders it as a PNG image
                response.setContentType("image/png");
                // Set the response content length header to inform the browser about the file size in bytes
                response.setContentLength(byteCount);
                // Get the output stream from the response to write binary data back to the client
                OutputStream out = response.getOutputStream();
                // Write the entire file byte array to the response output stream starting at index 0
                out.write(fileInBytes, 0, byteCount);
                // Flush the output stream to ensure all data is sent to the client immediately
                out.flush();
            } catch (FileNotFoundException e) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("""
                    <html>
                        <body>
                            <h1>Archivo no encontrado</h1>
                        </body>
                    </html>
                    """);
            }
        } else { // If is not the filename, return something else
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("""
                    <html>
                        <body>
                            <h1>Imagen no especificada en el parametro "file"</h1>
                        </body>
                    </html>
                    """);
        }
    }

    public void destroy() {
        System.out.println("HelloServlet destroyed");
    }
}