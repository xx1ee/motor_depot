package service;

import dao.StatDao;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.Statement;

public class StatService {
    private static final StatDao STAT_DAO = StatDao.getInstance();
    private static final StatService statService = new StatService();
    @SneakyThrows
    public void update(Integer stat) {
        STAT_DAO.update(stat);
    }
    @SneakyThrows
    public Integer select() {
        return STAT_DAO.select();
    }
    public static StatService getInstance() {
        return statService;
    }
}
