package repositorys;

import exceptions.RepositoryException;
import models.Mensch;
import enums.Geschlecht;
import utils.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.*;

public class MenschRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class);

    private final DatabaseConnection databaseConnection;

    public MenschRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public Mensch getMenschByName(String name) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            return getMenschByNameInternal(connection, name);
        } catch (SQLException e) {
            LOGGER.error("Verbindung fehlgeschlagen! {}", e.getMessage());
            throw new RepositoryException("Fehler beim Abrufen des Menschen mit dem Namen " + name, e);
        }
    }

    private Mensch getMenschByNameInternal(Connection connection, String name) throws SQLException {
        String sql = "SELECT m.lebewesen_id, m.alter, m.name, m.geburtstag, m.heute_tag, m.geschlecht, l.art, "
                + "(SELECT array_agg(hobby) FROM hobbies WHERE mensch_id = m.lebewesen_id) AS hobbies, "
                + "(SELECT array_to_json(array_agg(row_to_json(t))) "
                + "FROM ( "
                + "    SELECT hk.kategorie, array_agg(ha.name) AS namen "
                + "    FROM haustier_kategorien hk "
                + "    LEFT JOIN haustiere ha ON hk.id = ha.kategorie_id "
                + "    WHERE hk.mensch_id = m.lebewesen_id "
                + "    GROUP BY hk.kategorie "
                + ") t) AS haustiere "
                + "FROM mensch m "
                + "JOIN lebewesen l ON m.lebewesen_id = l.id "
                + "WHERE m.name = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            List<String> hobbies = getHobbies(resultSet);
            Map<String, List<String>> haustiere = getHaustiere(resultSet);

            return new Mensch(
                    resultSet.getInt("alter"),
                    resultSet.getString("name"),
                    resultSet.getTimestamp("geburtstag").toLocalDateTime(),
                    resultSet.getTimestamp("heute_tag").toLocalDateTime(),
                    hobbies,
                    haustiere,
                    Geschlecht.valueOf(resultSet.getString("geschlecht").toUpperCase())
            );
        }
        return null;
    }

    private List<String> getHobbies(ResultSet resultSet) throws SQLException {
        Array hobbiesArray = resultSet.getArray("hobbies");
        if (hobbiesArray != null) {
            String[] hobbiesStrArray = (String[]) hobbiesArray.getArray();
            return Arrays.asList(hobbiesStrArray);
        }
        return new ArrayList<>();
    }

    private Map<String, List<String>> getHaustiere(ResultSet resultSet) throws SQLException {
        String haustiereJson = resultSet.getString("haustiere");
        if (haustiereJson != null) {
            return JsonConverter.convertJsonToMap(haustiereJson);
        }
        return new HashMap<>();
    }
}