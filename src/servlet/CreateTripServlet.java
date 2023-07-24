package servlet;

import dto.CreateCarDto;
import dto.CreateTripDto;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TripService;

import java.io.IOException;

@WebServlet("/addTrip")
public class CreateTripServlet extends HttpServlet {
    private final TripService tripService = TripService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/addTrip.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var driver = req.getParameter("dr");
        var car = req.getParameter("car");
        var stDepart = req.getParameter("stDepart");
        var stArr = req.getParameter("stArr");
        var timeDepart = req.getParameter("timeDepart");
        var timeArr = req.getParameter("timeArr");
        var status = req.getParameter("status");
        var createTripDto = CreateTripDto.builder().driver(Integer.parseInt(driver)).car(Integer.parseInt(car)).st_depart(stDepart)
                .st_arr(stArr).time_depart(timeDepart).time_arr(timeArr).status(status).build();
        try {
            tripService.save(createTripDto);
            resp.sendRedirect("/trips");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}
