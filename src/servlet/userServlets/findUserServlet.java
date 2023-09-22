package servlet.userServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UsersService;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/deleteUser")
public class findUserServlet extends HttpServlet {
    private static final UsersService usersService = UsersService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/deleteUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usersService.delete(Objects.requireNonNull(usersService.findByEmailAndPassword(req.getParameter("email"),
                req.getParameter("password"))).get(0).getId());
        req.setAttribute("users", usersService.findAll());
        req.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(req, resp);
    }
}
