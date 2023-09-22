package servlet.tripServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TripService;

import java.io.IOException;

@WebServlet("/deleteTrip")
public class DeleteTripServlet extends HttpServlet {
    private static final TripService tripService = TripService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/deleteTrip.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tripService.delete(Integer.valueOf(req.getParameter("id")));
        req.setAttribute("trips", tripService.findAll());
        req.getRequestDispatcher("/WEB-INF/jsp/trips.jsp").forward(req, resp);
    }
}
