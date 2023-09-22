package servlet.driverServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;
import service.DriverService;

import java.io.IOException;

@WebServlet("/deleteDriver")
public class DeleteDriverServlet extends HttpServlet {
    private final DriverService driverService = DriverService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/deleteDriver.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        driverService.delete(req.getParameter("serialNum"));
        req.getRequestDispatcher("/WEB-INF/jsp/drivers.jsp").forward(req, resp);
    }
}
