/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import shoes.entity.Product;
import shoes.model.HomeModel;

/**
 *
 * @author abc
 */
@Controller
@RequestMapping(value = "/singleController")
public class SingleController {
    private HomeModel homeModel;
    public SingleController(){
        homeModel = new HomeModel();
    }
    @RequestMapping(value = "/single")
    public ModelAndView singleProduct(int productId){
        ModelAndView mav = new ModelAndView("single-product");
        Product singlePro = homeModel.getProductById(productId);
        mav.addObject("singlePro", singlePro);
        return mav;
    }
}
