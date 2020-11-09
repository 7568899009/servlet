package com.c.servlet;

import com.c.biz.impl.LogInBizImpl;
import com.c.entity.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(urlPatterns = "/servlet_e")
public class Servlet_e  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        LogInBizImpl logInBiz = new LogInBizImpl();
        boolean b = logInBiz.logIn(user);
        if ( b ) {
            req.setAttribute("user",user);
            req.getRequestDispatcher("/successServlet").forward(req,resp);

        } else {

            req.getRequestDispatcher("/faillServlet").forward(req,resp);
        }
    }
}
