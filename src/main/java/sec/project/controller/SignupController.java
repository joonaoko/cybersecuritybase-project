package sec.project.controller;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String loadForm(Authentication auth, Model model) {
        boolean isAdmin = false;
        if (auth.getAuthorities().toString().contains("ADMIN")) isAdmin = true;
        model.addAttribute("isAdmin", isAdmin);
        return "form";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(Authentication auth, @RequestParam String event, @RequestParam String address, @RequestParam(value="anon", required=false) String anon) throws SQLException {
        boolean anonymous = false;
        if (anon != null) anonymous = true;
        signupDao.save(new Signup(auth.getName(), address, event, anonymous));
        return "done";
    }
    
    @RequestMapping(value = "/signups", method = RequestMethod.GET)
    public String listSignups(Model model) throws SQLException {
        model.addAttribute("signups", signupDao.findAllNonAnonymous());
        return "signups";
    }
    
    @RequestMapping(value = "/signups", method = RequestMethod.POST)
    public String listEventForm(@RequestParam String eventname) {
        return "redirect:/signups/"+eventname;
    }
    
    @RequestMapping(value = "signups/{event}", method = RequestMethod.GET)
    public String listEventSignups(@PathVariable String event, Model model) throws SQLException {
        model.addAttribute("signups", signupDao.findEventSignups(event));
        return "signups";
    }
    
    @RequestMapping(value = "/adminsignups", method = RequestMethod.GET)
    public String listAdminSignups(Model model) throws SQLException {
        model.addAttribute("signups", signupDao.findAll());
        return "adminsignups";
    }
}
