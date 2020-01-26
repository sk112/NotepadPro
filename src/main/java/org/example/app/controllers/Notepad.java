package org.example.app.controllers;


import ch.qos.logback.core.db.dialect.DBUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.example.app.utils.DomainUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@Data
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

        //Updating currentDomain Variable.
//        this.setCurrentDomain(rndDomain);
        log.info("Random Domain: "+rndDomain);

        return "redirect:/note/"+rndDomain;
    }

    @GetMapping("/{param}")
    public String domainRoute(@PathVariable("param") String domain, Model model){

        //After implementing Auth, modify to check Auth credentials if required
        //Implement Auth Check Here   ---- to do -----

//        //updating currentDomain value, if not set.
//        if(currentDomain.equals("")){
//            this.setCurrentDomain(domain);
//        }

        String note = DomainUtils.checkAndGetNoteFromDomain(domain);
        Integer Id = DomainUtils.getSessionID(domain);
        /*Using ModelAndView
        ModelAndView mvc = new ModelAndView();

        mvc.addObject("note", note);
        mvc.setViewName("homepage");
        */

        model.addAttribute("note", note);
        model.addAttribute("note_length", note.length());
        model.addAttribute("domain", domain);
        model.addAttribute("Id", Id);

        return "home";
    }

    @PostMapping("/update/{param}")
    @ResponseBody
    public String updateNote(@PathVariable("param") String domain, HttpServletRequest request){

        String note = request.getParameter("textEditor");
        log.info(request.toString());


        DomainUtils.saveNoteInDomain(domain, note);
        return domain;
    }

}
