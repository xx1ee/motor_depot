package servlet.tripServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TripService;

import java.io.IOException;
import java.util.HashMap;
@WebServlet("/allTripsDepartOf")
public class AllTripsDepartOfServlet extends HttpServlet {
    private static final TripService tripService = TripService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/allTripsDepartOf.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("trips", tripService.findTripsDepartOf(req.getParameter("depart")));
        req.getRequestDispatcher("/WEB-INF/jsp/trips.jsp").forward(req, resp);
    }
}
