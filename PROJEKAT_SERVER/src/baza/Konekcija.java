package baza;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Konekcija {

    private static Konekcija instance;
    private Connection connection;

    private Konekcija() {

        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("config.properties")) {

            props.load(fis);

            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);

            System.out.println("Konekcija uspesno ostvarena.");

        } catch (IOException ex) {

            System.out.println("GRESKA: config.properties fajl nije pronadjen!");
            connection = null;

        } catch (SQLException ex) {

            System.out.println("GRESKA: Konekcija sa bazom nije uspostavljena!");
            connection = null;
        }
    }

    public static Konekcija getInstace() {

        if (instance == null) {
            instance = new Konekcija();
        }

        return instance;
    }

    public Connection getConnection() {

        if (connection == null) {
            throw new RuntimeException(
                    "Konekcija sa bazom nije uspostavljena."
            );
        }

        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}