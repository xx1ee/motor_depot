package dao;

import entity.Driver;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.Date;
import java.sql.Statement;
public class StatDao{
    private static final StatDao STAT_DAO = new StatDao();
    private static final String UPDATE_STAT = """
            UPDATE stat SET kolvo = ?
            """;
    private static final String SELECT_STAT = """
            SELECT * FROM public.stat
            """;

    @SneakyThrows
    public void update(Integer stat) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_STAT)) {
            preparedStatement.setInt(1, stat);
            System.out.println(preparedStatement.executeUpdate() > 0);
        }
    }
    @SneakyThrows
    public Integer select() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SELECT_STAT)) {
            var res = preparedStatement.executeQuery();
            if (res.next()) {
                return res.getObject("kolvo", Integer.class);
            }
        }
        return 0;
    }
    public static StatDao getInstance() {
        return STAT_DAO;
    }

}
