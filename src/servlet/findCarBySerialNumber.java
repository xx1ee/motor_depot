package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;

import java.io.IOException;

@WebServlet("/findCarBySerialNumber")
public class findCarBySerialNumber extends HttpServlet {
    private final CarService carService = CarService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/findCarBySerialNumber.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", carService.findBySerialNum(req.getParameter("serialNum")));
        req.setAttribute("title", "Найденное авто");
        req.getRequestDispatcher("/WEB-INF/jsp/cars.jsp").forward(req, resp);
    }
}
