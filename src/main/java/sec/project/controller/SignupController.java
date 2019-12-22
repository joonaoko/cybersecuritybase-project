package sec.project.controller;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import sec.project.repository.AccountRepository;
import sec.project.db.Database;
import sec.project.db.SignupDao;
import sec.project.domain.Signup;

@Controller
public class SignupController {
    Database db = sec.project.CyberSecurityBaseProjectApplication.db;
    SignupDao signupDao = new SignupDao(db);
    
    @Autowired
    private AccountRepository accountRepository;
    
    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm(Authentication auth) {
        return "form";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) throws SQLException {
        signupDao.save(new Signup(name, address));
        return "done";
    }
    
    @RequestMapping(value = "/signups", method = RequestMethod.GET)
    public String listSignups(Model model) throws SQLException {
        model.addAttribute("signups", signupDao.findAll());
        return "signups";
    }
}
