package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DriverService;

import java.io.IOException;

@WebServlet("/findDriverBySerialNumber")
public class findDriverBySerialNumServlet extends HttpServlet {
    private final DriverService driverService = DriverService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/findDriverBySerialNumber.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        driverService.findBySerialNum(req.getParameter("serialNum"));
        req.getRequestDispatcher("/WEB-INF/jsp/drivers.jsp").forward(req, resp);
    }
}
