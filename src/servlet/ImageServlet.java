package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.ImageService;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {
    private final ImageService imageService = ImageService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var requestURI = req.getRequestURI();
        String imgPath = requestURI.replace("/images", "");
        System.out.println(imageService.get(imgPath));
        imageService.get(imgPath).ifPresentOrElse(
                img -> {
                    writeImg(img, resp);
                }, () -> resp.setStatus(404));
    }
    @SneakyThrows
    private void writeImg(InputStream imgPath, HttpServletResponse resp) {
        try (imgPath; var outputStream = resp.getOutputStream()) {
            int currByte;
            while ((currByte = imgPath.read()) != -1) {
                outputStream.write(currByte);
            }
        }
    }
}
