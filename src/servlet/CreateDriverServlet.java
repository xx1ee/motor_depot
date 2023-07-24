package servlet;

import dto.CreateCarDto;
import dto.CreateDriverDto;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DriverService;
import util.LocalDateFormatter;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/addDriver")
public class CreateDriverServlet extends HttpServlet {
    private final DriverService driverService = DriverService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/addDriver.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var name = req.getParameter("name");
        var birth = req.getParameter("birth");
        var serialNumber = req.getParameter("serialNumber");
        var status = req.getParameter("status");
        var createDriverDto = CreateDriverDto.builder().name(name).
                birth(birth).
                serial_number(serialNumber).
                status(status).
                build();
        try {
            driverService.save(createDriverDto);
            resp.sendRedirect("/drivers");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}
