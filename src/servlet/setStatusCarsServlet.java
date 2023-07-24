package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;

import java.io.IOException;

@WebServlet("/setStatusCars")
public class setStatusCarsServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carService.setStatusCars();
        req.setAttribute("title", "Все авто");
        req.setAttribute("drivers", carService.findAll());
        req.getRequestDispatcher("WEB-INF/jsp/car.jsp").forward(req, resp);
    }
}
