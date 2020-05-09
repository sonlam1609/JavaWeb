/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoes.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import shoes.entity.Billretail;
import shoes.entity.Product;
import shoes.model.HomeModel;

/**
 *
 * @author abc
 */
@Controller
@RequestMapping(value = "/cartController")
public class CartController {

    private HomeModel homeModel;

    public CartController() {
        homeModel = new HomeModel();
    }

    @RequestMapping(value = "/addBillretail")
    public String addBillretail(HttpSession session, int productId) {
        Product proAdd = homeModel.getProductById(productId);
        List<Billretail> listBillretail = (List<Billretail>) session.getAttribute("listBillretail");
        if (listBillretail == null) {
            listBillretail = new ArrayList<>();
            Billretail bill = new Billretail(proAdd, 1);
            listBillretail.add(bill);
        } else {
            boolean check = false;
            for (Billretail bill : listBillretail) {
                if (bill.getProduct().getProductId() == productId) {
                    bill.setQuantity(bill.getQuantity() + 1);
                    check = true;
                    break;
                }
            }
            if (check == false) {
                Billretail bill = new Billretail(proAdd, 1);
                listBillretail.add(bill);
            }
        }
        session.setAttribute("listBillretail", listBillretail);
        session.setAttribute("totalAmount", callTotalAmount(listBillretail));
        session.setAttribute("quantity", callTQuantity(listBillretail));
        return "redirect:cart.htm";
    }

    @RequestMapping(value = "/check")
    public ModelAndView checkout() {
        ModelAndView mav = new ModelAndView("checkout");
        return mav;
    }

    @RequestMapping(value = "/checkout")
    public String chekout(HttpSession session, @RequestParam("userid") int userid) {
        List<Billretail> list = (List<Billretail>) session.getAttribute("listBillretail");
        double totalAmount = (double) session.getAttribute("totalAmount");
        boolean check = homeModel.checkout(list, userid, totalAmount);
        if (check) {
            if (userid == 0) {
                session.setAttribute("alter", "Ban can phai dang nhap");
                return "redirect:check.htm";
            } else {
                session.setAttribute("listBillretail", null);
                session.setAttribute("mess", "Dat hang thanh cong");
                return "redirect:check.htm";
            }
        } else {
            session.setAttribute("mess", "Dat hang that bai");
            return "redirect:check.htm";
        }

    }

    @RequestMapping(value = "/cart")
    public ModelAndView cart() {
        ModelAndView mav = new ModelAndView("cart");
        return mav;
    }

    public double callTotalAmount(List<Billretail> listBillretail) {
        double totalAmount = 0;
        for (Billretail bill : listBillretail) {
            totalAmount += (bill.getProduct().getPrice() - bill.getProduct().getPrice() * bill.getProduct().getDiscount() / 100) * bill.getQuantity();
        }
        return totalAmount;
    }

    public int callTQuantity(List<Billretail> listBillretail) {
        int quantity = 0;
        for (Billretail bill : listBillretail) {
            quantity += bill.getQuantity();
        }
        return quantity;
    }

    @RequestMapping(value = "/remove")
    public String removeBillretail(HttpSession session, int productId) {
        List<Billretail> listBillretail = (List<Billretail>) session.getAttribute("listBillretail");
        for (int i = 0; i < listBillretail.size(); i++) {
            if (listBillretail.get(i).getProduct().getProductId() == productId) {
                listBillretail.remove(i);
                break;
            }
        }
        session.setAttribute("listBillretail", listBillretail);
        session.setAttribute("totalAmount", callTotalAmount(listBillretail));
        return "cart";
    }

    @RequestMapping(value = "/updateCart")
    public String updateCart(HttpSession session, HttpServletRequest request) {
        List<Billretail> listBillretail = (List<Billretail>) session.getAttribute("listBillretail");
        String arrQuantity[] = request.getParameterValues("quantitys");
        for (int i = 0; i < listBillretail.size(); i++) {
            listBillretail.get(i).setQuantity(Integer.parseInt(arrQuantity[i]));
        }
        session.setAttribute("listBillretail", listBillretail);
        session.setAttribute("totalAmount", callTotalAmount(listBillretail));
        return "cart";
    }
}
