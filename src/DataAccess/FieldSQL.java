package DataAccess;

import System.FootballObjects.Field;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FieldSQL implements DataBase<Field> {
    private static FieldSQL ourInstance = new FieldSQL();

    public static FieldSQL getInstance() {
        return ourInstance;
    }

    private FieldSQL() {
    }

    @Override
    public Optional<Field> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Field> getAll() {
        return null;
    }

    @Override
    public void save(Field field) throws SQLException {

    }

    @Override
    public void update(Field field, String[] params) {

    }

    @Override
    public void delete(Field field) {

    }
}
