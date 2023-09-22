package servlet.carServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;

import java.io.IOException;

@WebServlet("/findFreeCar")
public class FindFreeCar extends HttpServlet {
    private final CarService carService = CarService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Доступные авто");
        req.setAttribute("cars", carService.findFreeCar());
        req.getRequestDispatcher("/WEB-INF/jsp/car.jsp").forward(req, resp);
    }
}
