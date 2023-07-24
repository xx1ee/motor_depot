package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TripService;

import java.io.IOException;

@WebServlet("/completeTrip")
public class CompleteTripServlet extends HttpServlet {
    private final TripService tripService = TripService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tripService.zaversh();
        req.setAttribute("trips", tripService.findAll());
        req.getRequestDispatcher("WEB-INF/jsp/trips.jsp").forward(req, resp);
    }
}
