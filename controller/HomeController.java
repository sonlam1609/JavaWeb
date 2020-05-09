/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoes.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import shoes.entity.Product;
import shoes.entity.Users;
import shoes.model.HomeModel;
import shoes.model.UserModel;

/**
 *
 * @author abc
 */
@Controller
@RequestMapping(value = "/homeController")
public class HomeController {

    private HomeModel homeModel;
    private UserModel usermodel;

    public HomeController() {
        homeModel = new HomeModel();
        usermodel = new UserModel();
    }

    @RequestMapping(value = "/view")
    public ModelAndView getProView() {
        ModelAndView mav = new ModelAndView("index_1");
        List<Product> listProView = homeModel.getProductView();
        mav.addObject("listProView", listProView);
        return mav;
    }

    @RequestMapping(value = "/initRegister")
    public ModelAndView initRegister() {
        ModelAndView mav = new ModelAndView("register");
        Users userInsert = new Users();
        mav.addObject("userInsert", userInsert);
        return mav;
    }

    @RequestMapping(value = "/insertRegister")
    public void insertUser(Users userInsert, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean check = usermodel.insertUser(userInsert);
        PrintWriter out = response.getWriter();
        boolean checkuser = usermodel.checkUser(userInsert.getUsername());
        if (check && checkuser) {
//            return "redirect:view.htm";
            RequestDispatcher rd = request.getRequestDispatcher("view.htm");
            rd.forward(request, response);
        } else {
            out.println("<font color='red'><b>Tai khoan bi trung</b></font>");
            RequestDispatcher rd = request.getRequestDispatcher("initRegister.htm");
            rd.include(request, response);
        }
    }

    @RequestMapping(value = "/initlogin")
    public ModelAndView showLogin() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping(value = "/admin")
    public ModelAndView checkAdmin() {
        ModelAndView mav = new ModelAndView("admin");
        return mav;
    }

    @RequestMapping(value = "/login")
    public void executeLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("username");
        String pass = request.getParameter("userpass");
        Users users = usermodel.getUserByUsername(name);
        PrintWriter out = response.getWriter();
        if (usermodel.checkLogin(name, pass)) {
//            return "redirect:view.htm";
            if (usermodel.checkAdmin(name, pass)) {
                session.setAttribute("users", users);
                RequestDispatcher rd = request.getRequestDispatcher("admin.htm");
                rd.forward(request, response);
            } else {
                session.setAttribute("users", users);
                RequestDispatcher rd = request.getRequestDispatcher("view.htm");
                rd.forward(request, response);
            }
        } else {
            out.println("<font color='red'><b>Sai tai khoan hoac mat khau</b></font>");
            RequestDispatcher rd = request.getRequestDispatcher("initlogin.htm");
            rd.include(request, response);
        }
    }

    @RequestMapping(value = "/searchName")
    public ModelAndView sreachListName(HttpServletRequest request) {
        String name = request.getParameter("checkname");
        ModelAndView mav = new ModelAndView("shop-name");
        List<Product> listName = homeModel.searchListName(name);
        mav.addObject("listName", listName);
        return mav;
    }
}
