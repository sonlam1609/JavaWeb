/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoes.model;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import shoes.entity.Bill;
import shoes.entity.Billretail;
import shoes.entity.Categorios;
import shoes.entity.Product;
import shoes.entity.Users;
import shoes.util.HibernateUtil;

/**
 *
 * @author abc
 */
public class HomeModel {

    public List<Product> getAllProduct() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        List<Product> listPro = session.createQuery("from Product").list();
        session.getTransaction().commit();
        session.close();
        return listPro;
    }

    public List<Product> getProductView() {
        //Lay san pham xem cao nhat
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria cr = session.createCriteria(Product.class);
        cr.addOrder(Order.desc("productView"));
        cr.setMaxResults(12);
        List<Product> listProView = cr.list();
        session.getTransaction().commit();
        session.close();
        return listProView;
    }


    public List<Product> getProductSales() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Product ORDER BY discount DESC");
        query.setMaxResults(12);
        List<Product> listProSales = query.list();
        session.getTransaction().commit();
        session.close();
        return listProSales;
    }

    public Product getProductById(int product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Product where productId=:product");
        query.setInteger("product", product);
        Product pro = (Product) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return pro;
    }

    public long countCate(int catId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("Select count(productId) from Product where categorios.catId=:catId");
        query.setInteger("catId", catId);
        return (long) query.uniqueResult();
    }

    public long searchName(String keyword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String HQL = "SELECT count(productId) from Product where productName like :productName OR productName like :name";
        Query query = session.createQuery(HQL);
        query.setString("productName", "%" + keyword.toUpperCase() + "%");
        query.setString("name", "%" + keyword.toLowerCase() + "%");

        return (long) query.uniqueResult();
    }
    public List<Product> searchListName(String keyword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("SELECT p FROM Product p WHERE p.productName like :productName OR p.productName like :name");
        query.setString("productName", "%" + keyword.toUpperCase() + "%");
        query.setString("name", "%" + keyword.toLowerCase() + "%");
        List<Product> listpro = query.list();
        session.getTransaction().commit();
        session.close();
        return listpro;
    }
    public int calPages(int pageSize, int catId) {
        //pageSize la so luong ban ghi lon nhat trong trang
        long totalRecords = countCate(catId);
        int totalPages;
        if (totalRecords % pageSize == 0) {
            totalPages = (int) totalRecords / pageSize;
        } else {
            totalPages = (int) (totalRecords / pageSize) + 1;
        }
        return totalPages;
    }

    public List<Product> getUsingCriteria(int position, int pagesSize, int catId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.like("categorios.catId", catId));
        criteria.setFirstResult(position);
        criteria.setMaxResults(pagesSize);
        return criteria.list();
    }
    public boolean checkout(List<Billretail> list, int userid, double totalAmount) {
        HomeModel home = new  HomeModel();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        try {
            int id = 0;
            Users u = new Users();
            u.setUserId(userid);
            Bill bill = new Bill();
            bill.setUsers(u);
            bill.setTotalaman(totalAmount);
            bill.setCreated(new Date());
            session.save(bill);
            Query query = session.createQuery("select max(billId) from Bill");
            id = (int) query.uniqueResult(); 
            Bill b = new Bill();
            b.setBillId(id);
            for (int i = 0; i < list.size(); i++) {
                Billretail get = list.get(i);
                get.setBill(b);
                session.save(get);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return false;
    }
    
    public static void main(String[] args) {
        HomeModel home = new  HomeModel();
        List<Product> list = home.getAllProduct();
        for (Product pro : list) {
            System.out.println("name: "+pro.getProductName());
        }
        
    }

}
