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
        
        sqlite.add("CREATE TABLE Signup (id integer PRIMARY KEY, name varchar(255), address varchar(255));");
        sqlite.add("INSERT INTO Signup (name, address) VALUES ('Erik Example', 'Test Address 1');");
        sqlite.add("INSERT INTO Signup (name, address) VALUES ('Tessa Tester', 'Test Address 2');");
        
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
