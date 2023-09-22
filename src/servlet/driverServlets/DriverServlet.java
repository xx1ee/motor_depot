package servlet.driverServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;
import service.DriverService;

import java.io.IOException;
import java.util.List;

@WebServlet("/drivers")
public class DriverServlet extends HttpServlet {
    private final DriverService driverService = DriverService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("driverId") != null) {
            req.setAttribute("drivers", List.of(driverService.findById(Integer.parseInt(req.getParameter("driverId")))));
            req.setAttribute("title", "Водитель назначенный на рейс");
        } else {
            req.setAttribute("drivers", driverService.findAll());
            req.setAttribute("title", "Водители автобазы");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/drivers.jsp").forward(req, resp);
    }
}
