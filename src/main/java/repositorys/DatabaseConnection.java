package repositorys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import configs.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class);

    private static final AppConfig CONFIG = AppConfig.getInstance();

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(CONFIG.getDbUrl(), CONFIG.getDbUser(), CONFIG.getDbPassword());
        } catch (SQLException e) {
            LOGGER.error("Verbindung fehlgeschlagen! {}", e.getMessage());
            return null;
        }
    }
}