package servlet;

import dto.CreateUsersDto;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UsersService;

import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024*1024)
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var email = req.getParameter("email");
        var password = req.getParameter("password");
        var role = req.getParameter("role");
        var image = req.getPart("image");
        var createUsersDto = CreateUsersDto.builder().email(email).password(password).role(role)
                .image(image).build();
        try {
            UsersService.getInstance().save(createUsersDto);
            resp.sendRedirect("/login");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}
