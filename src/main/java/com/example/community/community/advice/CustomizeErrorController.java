package com.example.community.community.advice;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomizeErrorController {
    @ExceptionHandler({Exception.class})
    public ModelAndView handlerArithmeticException(Exception e,
                                                   Model model) {
        model.addAttribute("er","sdasd");
        ModelAndView mv = new ModelAndView("error");//要跳转的页面
        return mv;
    }
}
