package servlet.tripServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TripService;

import java.io.IOException;
@WebServlet("/findTripsBySerialNumber")
public class findTripsBySeriaNumberServlet extends HttpServlet {
    private final TripService tripService = TripService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/findTripsBySerialNumber.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var serialNum = req.getParameter("serialNum");
        req.setAttribute("trips", tripService.findBySerialNumber(serialNum));
        req.getRequestDispatcher("/WEB-INF/jsp/trips.jsp").forward(req, resp);
    }
}
