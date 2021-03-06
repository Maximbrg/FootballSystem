package DataAccess;

import System.FootballObjects.Event.EventLog;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EventLogSQL implements DataBase<EventLog> {
    private static EventLogSQL ourInstance = new EventLogSQL();

    public static EventLogSQL getInstance() {
        return ourInstance;
    }

    private EventLogSQL() {
    }

    @Override
    public Optional<EventLog> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<EventLog> getAll() {
        return null;
    }

    @Override
    public void save(EventLog eventLog) throws SQLException {

    }

    @Override
    public void update(EventLog eventLog, String[] params) {

    }

    @Override
    public void delete(EventLog eventLog) {

    }
}
