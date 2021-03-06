package DataAccess;

import System.FootballObjects.Season;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SeasonSQL implements DataBase<Season> {
    private static SeasonSQL ourInstance = new SeasonSQL();

    public static SeasonSQL getInstance() {
        return ourInstance;
    }

    private SeasonSQL() {
    }

    @Override
    public Optional<Season> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Season> getAll() {
        return null;
    }

    @Override
    public void save(Season season) throws SQLException {

    }

    @Override
    public void update(Season season, String[] params) {

    }

    @Override
    public void delete(Season season) {

    }
}
