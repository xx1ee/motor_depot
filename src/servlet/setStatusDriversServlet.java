package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DriverService;

import java.io.IOException;

@WebServlet("/setStatusDrivers")
public class setStatusDriversServlet extends HttpServlet {
    private final DriverService driverService = DriverService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Все водители");
        req.setAttribute("drivers", driverService.findAll());
        req.getRequestDispatcher("WEB-INF/jsp/drivers.jsp").forward(req, resp);
    }
}
