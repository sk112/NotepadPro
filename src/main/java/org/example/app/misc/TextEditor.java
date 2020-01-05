package org.example.app.misc;
import org.example.app.model.NoteEntity;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TextEditor extends SimpleFormController{
    public TextEditor(){
        setCommandClass(NoteEntity.class);
        setCommandName("customerForm");
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
                                    HttpServletResponse response, Object command, BindException errors)
            throws Exception {

        NoteEntity customer = (NoteEntity)command;
        return new ModelAndView("home","NoteEntity",customer);

    }
}
