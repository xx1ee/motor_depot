package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/cars")
public class CarServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("carId") != null) {
            req.setAttribute("cars", List.of(carService.findById(Integer.parseInt(req.getParameter("carId")))));
            req.setAttribute("title", "Автомобиль назначенный на рейс");
        } else {
            req.setAttribute("cars", carService.findAll());
            req.setAttribute("title", "Автомобили принадлежащие автобазе");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/car.jsp").forward(req, resp);
    }
}
