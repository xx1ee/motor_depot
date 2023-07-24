package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;
import service.DriverService;

import java.io.IOException;
@WebServlet("/findFreeDriver")
public class FindFreeDriverServlet extends HttpServlet {
    private final DriverService driverService = DriverService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Доступные водители");
        req.setAttribute("drivers", driverService.findFree());
        req.getRequestDispatcher("/WEB-INF/jsp/drivers.jsp").forward(req, resp);
    }
}
