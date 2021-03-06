package sec.project.db;

import java.sql.*;
import java.util.*;

public interface Dao<T, K> {
    T findOne(K key) throws SQLException;
    
    List<T> findAll() throws SQLException;
    
    List<T> findAllNonAnonymous() throws SQLException;
    
    List<T> findEventSignups(String eventname) throws SQLException;
    
    void save(T object) throws SQLException;
}
