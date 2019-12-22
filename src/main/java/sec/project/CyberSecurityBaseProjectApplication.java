package sec.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sec.project.db.Database;

@SpringBootApplication()
public class CyberSecurityBaseProjectApplication {
    public static Database db;

    public static void main(String[] args) throws Throwable {
        db = new Database("jdbc:sqlite:database.db");
        db.init();
        
        SpringApplication.run(CyberSecurityBaseProjectApplication.class);
    }
}
