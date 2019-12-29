package sec.project.db;
import java.sql.*;
import java.util.*;
import sec.project.domain.Signup;

public class SignupDao implements Dao<Signup, Long> {
    private Database db;
    
    public SignupDao(Database db) {
        this.db = db;
    }

    @Override
    public Signup findOne(Long id) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Test WHERE id = "+id);
        
        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        
        String username = rs.getString("username");
        String address = rs.getString("address");
        String event = rs.getString("event");
        boolean anonymous = rs.getBoolean("anonymous");
        
        Signup s = new Signup(id, username, address, event, anonymous);
        
        rs.close();
        stmt.close();
        conn.close();
        
        return s;
    }

    @Override
    public List<Signup> findAll() throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Signup");
        
        ResultSet rs = stmt.executeQuery();
        List<Signup> signups = new ArrayList<>();
        while(rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("username");
            String address = rs.getString("address");
            String event = rs.getString("event");
            boolean anonymous = rs.getBoolean("anonymous");
            
            signups.add(new Signup(id, name, address, event, anonymous));
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return signups;
    }
    
    @Override
    public List<Signup> findAllNonAnonymous() throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Signup WHERE anonymous='false'");
        
        ResultSet rs = stmt.executeQuery();
        List<Signup> signups = new ArrayList<>();
        while(rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("username");
            String address = rs.getString("address");
            String event = rs.getString("event");
            boolean anonymous = rs.getBoolean("anonymous");
            
            signups.add(new Signup(id, name, address, event, anonymous));
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return signups;
    }
    
    @Override
    public List<Signup> findEventSignups(String eventname) throws SQLException {
        Connection conn = db.getConnection();
        String sqlite = "SELECT * FROM Signup WHERE anonymous='false' AND event='"+eventname+"';";
        PreparedStatement stmt = conn.prepareStatement(sqlite);
        
        ResultSet rs = stmt.executeQuery();
        List<Signup> signups = new ArrayList<>();
        while(rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("username");
            String address = rs.getString("address");
            String event = rs.getString("event");
            boolean anonymous = rs.getBoolean("anonymous");
            
            signups.add(new Signup(id, name, address, event, anonymous));
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return signups;
    }

    @Override
    public void save(Signup s) throws SQLException {
        Connection conn = db.getConnection();
        String sqlite = "INSERT INTO Signup (username, address, event, anonymous) VALUES ('"+s.getUsername()+"', '"+s.getAddress()+"', '"+s.getEvent()+"', '"+s.getAnonymous()+"');";
        PreparedStatement stmt = conn.prepareStatement(sqlite);
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
    }
}
