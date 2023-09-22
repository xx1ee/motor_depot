import dao.CarDao;
import dao.DriverDao;
import dao.TripDao;
import dao.UsersDao;
import entity.Car;
import entity.Driver;
import entity.Trip;
import entity.Users;
import util.PropertiesUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println(TripDao.getInstance().findAll());
    }
}
