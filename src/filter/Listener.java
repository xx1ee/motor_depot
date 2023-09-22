package filter;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.Getter;
import service.StatService;

@WebListener
public class Listener implements ServletContextListener {
    public static final StatService STAT_SERVICE = StatService.getInstance();

    private static int hitCount;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        hitCount = STAT_SERVICE.select();
        hitCount++;
        System.out.println("Visits count is :" + hitCount);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        STAT_SERVICE.update(hitCount);
    }
    public static int getHitCount() {
        return hitCount;
    }

}
