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
        
        String name = rs.getString("name");
        String address = rs.getString("address");
        
        Signup s = new Signup(id, name, address);
        
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
            String name = rs.getString("name");
            String address = rs.getString("address");
            
            signups.add(new Signup(id, name, address));
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return signups;
    }

    @Override
    public void save(Signup s) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Signup (name, address) VALUES ('"+s.getName()+"', '"+s.getAddress()+"');");
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
    }
}
