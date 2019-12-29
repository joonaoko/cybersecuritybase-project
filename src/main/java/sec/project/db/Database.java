package sec.project.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String databaseAddress;

    public Database(String databaseAddress) throws Exception {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        if (dbUrl != null && dbUrl.length() > 0) {
            return DriverManager.getConnection(dbUrl);
        }
        
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> sqlite = new ArrayList<>();
        
        sqlite.add("CREATE TABLE Signup (id integer PRIMARY KEY, username varchar(255), address varchar(255), event varchar(255), anonymous boolean);");
        sqlite.add("INSERT INTO Signup (username, address, event, anonymous) VALUES ('user1', 'Test Address 1', '100m sprint', 'false');");
        sqlite.add("INSERT INTO Signup (username, address, event, anonymous) VALUES ('user1', 'Test Address 1', '200m sprint', 'true');");
        sqlite.add("INSERT INTO Signup (username, address, event, anonymous) VALUES ('user2', 'Test Address 2', 'longjump', 'false');");
        sqlite.add("INSERT INTO Signup (username, address, event, anonymous) VALUES ('user2', 'Test Address 2', 'triple jump', 'true');");
        
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            for (String s : sqlite) {
                System.out.println("Running command: " + s);
                st.executeUpdate(s);
            }

        } catch (Throwable t) {
            System.out.println("Error: " + t.getMessage());
        }
    }
}
