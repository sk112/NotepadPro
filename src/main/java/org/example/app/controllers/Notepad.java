package org.example.app.controllers;


import lombok.extern.slf4j.Slf4j;

import org.example.app.utils.DomainUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping(value = "/note", method = RequestMethod.GET)
public class Notepad {

    boolean newDomain = false;

    @GetMapping("")
    public String init(){
        return "redirect:/note/";
    }


    @GetMapping("/")
    public String defaultRoute(){

        //Since Empty, creation of a new Domain is required.
        newDomain = true;

        //Create and return New Domain
        String rndDomain = DomainUtils.createNewDomain();
        log.info("Random Domain: "+rndDomain);

        return "redirect:/note/"+rndDomain;
    }

    @GetMapping("/{param}")
    public String domainRoute(@PathVariable("param") String domain, Model model){

        //After implementing Auth, modify to check Auth credentials if required
        //Implement Auth Check Here   ---- to do -----

        String note = DomainUtils.checkAndGetNoteFromDomain(domain);

        /*Using ModelAndView
        ModelAndView mvc = new ModelAndView();

        mvc.addObject("note", note);
        mvc.setViewName("homepage");
        */

        model.addAttribute("note", note);
        model.addAttribute("note_length", note.length());

        return "home";
    }

}
