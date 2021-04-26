package org.judy.main;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/account")
@Log4j
public class HomeController {

   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

   /**
    * Simply selects the home view to render by returning its name.
    */
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String home(Locale locale, Model model) {
      logger.info("Welcome home! The client locale is {}.", locale);

      Date date = new Date();
      DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

      String formattedDate = dateFormat.format(date);

      model.addAttribute("serverTime", formattedDate);

      return "home";
   }
   
   @GetMapping("/login")
   public void getLogin() {
      
   }
   
   @GetMapping("/register")
   public void getRegister() {
      
   }
   
   @GetMapping("/customLogin")
   public void customLogin(String error, String logout, Model model) {
      
      log.info("error"+error);
      log.info("logout"+logout);
      
      if(error != null) {
         model.addAttribute("error", "Login Error");
      }
      if(logout != null) {
         model.addAttribute("logout", "logOUT!!!");
      }
      
   }

}
