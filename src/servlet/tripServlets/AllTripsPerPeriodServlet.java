package servlet.tripServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TripService;

import java.io.IOException;
@WebServlet("/allTripsPerPeriod")
public class AllTripsPerPeriodServlet extends HttpServlet {
    private final TripService tripService = TripService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/allTripsPerPeriod.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var start = req.getParameter("startDate");
        var end = req.getParameter("endDate");
        req.setAttribute("trips", tripService.findPerDate(start, end));
        req.getRequestDispatcher("/WEB-INF/jsp/trips.jsp").forward(req, resp);
    }
}
