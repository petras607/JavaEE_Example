package pieniazek.clnt;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.axis.encoding.Base64;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private static final String DATA_DIRECTORY = "c:/data";
	int maxFileSize = 5000 * 1024;
	int maxMemSize = 5000 * 1024;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Part filePart = request.getPart("file");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		
		// Sprawdzamy czy rozszerzenie jest .png, .jpg lub .bmp
		if (!fileName.contains(".png") || !fileName.contains(".jpg") || !fileName.contains(".bmp"))
		{
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<html>");
			writer.println("Strona obsluguje tylko pliki .png, .jpg oraz .bmp<br>");
			writer.println("</html>");
		}
		
		File uploads = new File(DATA_DIRECTORY);
		
		File file = new File(uploads, fileName);

		if (file.exists())
		{	
			file.delete();
			file = new File(uploads, fileName);
		}
		
		try (InputStream fileContent = filePart.getInputStream())
		{
			Files.copy(fileContent, file.toPath());
		}
		
		// Tworzymy beana
		PieniazekBean bean = new PieniazekBean();
		String radio = (String)request.getAttribute("radios");
		bean.setRadios(radio);
		bean.setFileName(DATA_DIRECTORY + "/" + fileName);
		//bean.setWynik("");
		
		BufferedImage bufferedImage = ImageIO.read(file);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", outputStream);
		String encodedImage = Base64.encode(outputStream.toByteArray());
		
		bean.setImage("data:image/png;base64," + encodedImage);
		
		request.setAttribute("Pieniazek", bean);
		
		// displays done.jsp page after upload finished
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
