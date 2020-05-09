/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoes.controller;

import shoes.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import shoes.model.HomeModel;

/**
 *
 * @author abc
 */
@Controller
@RequestMapping(value = "/phanTrangController")
public class PhanTrangController {

    private HomeModel proModel;

    public PhanTrangController() {
        proModel = new HomeModel();
    }

    @RequestMapping(value = "/paginationOfProduct")
    public ModelAndView paginationOfProduct(int page, int catId) {
        ModelAndView mav = new ModelAndView("shop-grid");
        int pageSize = 6;
        int totalPages = proModel.calPages(pageSize,catId);
        int position = (page - 1) * pageSize;
        List<Integer> listPage = new ArrayList<>();

        for (int i = 1; i <= totalPages; i++) {
            listPage.add(i);
        }

        List<Product> listPro = proModel.getUsingCriteria(position, pageSize, catId);
        mav.addObject("listProduct", listPro);
        mav.addObject("totalPages", totalPages);
        mav.addObject("listPage", listPage);
        mav.addObject("catId", catId);
        //tra lai mav
        return mav;
       
    }
    
}
