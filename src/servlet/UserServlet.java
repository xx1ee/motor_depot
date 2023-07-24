package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DriverService;
import service.UsersService;

import java.io.IOException;
@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private final UsersService usersService = UsersService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", usersService.findAll());
        req.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(req, resp);
    }
}
