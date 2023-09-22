package servlet.carServlets;

import dto.CreateCarDto;
import dto.CreateUsersDto;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;
import service.UsersService;

import java.io.IOException;

@WebServlet("/addCar")
public class CreateCarServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/addCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var model = req.getParameter("model");
        var number = req.getParameter("number");
        var status = req.getParameter("status");
        var createCarDto = CreateCarDto.builder().model(model).number(number).status(status).build();
        try {
            carService.save(createCarDto);
            resp.sendRedirect("/car");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}
