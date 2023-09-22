package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    private static final List<String> PUBLIC_PATH = List.of("/login", "/registration");
    private static final List<String> ADMIN_PATH = List.of("/addCar", "/deleteCar", "/setStatusCars", "/addDriver",
            "/deleteDriver", "/setStatusDrivers", "/addTrip", "/deleteTrip", "/completeTrip", "/users", "/deleteUser");
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (PUBLIC_PATH.contains(uri) || (isUserLoggedIn(servletRequest) && !ADMIN_PATH.contains(uri)) || (isAdminLoggedIn(servletRequest) && ADMIN_PATH.contains(uri))) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            var prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : "/login");
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getSession().getAttribute("user") != null;
    }

    private boolean isAdminLoggedIn(ServletRequest servletRequest) {
        System.out.println(((HttpServletRequest) servletRequest).getSession().getAttribute("user"));
        if (((HttpServletRequest) servletRequest).getSession().getAttribute("user") != null) {
            return ((HttpServletRequest) servletRequest).getSession().getAttribute("user").toString().contains("роль - ADMIN");
        } else return false;
    }
}
