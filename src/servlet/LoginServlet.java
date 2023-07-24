package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UsersService;

import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024*1024)
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UsersService usersService = UsersService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var email = req.getParameter("email");
        var password = req.getParameter("password");
        if (usersService.findByEmailAndPassword(email, password).isPresent()) {
            req.getSession().setAttribute("user", usersService.findByEmailAndPassword(email, password));
            resp.sendRedirect("/start");
        } else {
            doGet(req, resp);
        }
    }
}
