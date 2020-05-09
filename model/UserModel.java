/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoes.model;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import shoes.entity.Bill;
import shoes.entity.Billretail;
import shoes.entity.Product;
import shoes.entity.Users;
import shoes.util.HibernateUtil;

/**
 *
 * @author abc
 */
public class UserModel {

    public List<Users> getAllUser() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from Users");
        List<Users> listuser = query.list();
        session.getTransaction().commit();
        session.close();
        return listuser;
    }

    public boolean insertUser(Users user) {
        boolean check = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(user);
            session.getTransaction().commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return check;
    }

    public boolean checkUser(String username) {
        boolean check = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Users user = null;
        try {
            Query query = session.createQuery("from Users where username=:username");
            query.setString("username", username);
            user = (Users) query.uniqueResult();
            session.getTransaction().commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return check;
    }

    public Users getUserByUsername(String username) {
        boolean check = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Users user = null;
        try {
            Query query = session.createQuery("from Users where username=:username");
            query.setString("username", username);
            user = (Users) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return user;
    }

    public boolean checkLogin(String username, String userpass) {
        boolean check = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        List<Users> user = null;
        try {
            Query query = session.createQuery("from Users where username=:username and userpass=:userpass");
            query.setString("username", username);
            query.setString("userpass", userpass);
            user = query.list();
            session.getTransaction().commit();
            if (user.size() >= 1) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            check = false;
        }
        session.close();
        return check;
    }

    public boolean checkAdmin(String username, String userpass) {
        boolean check = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        List<Users> user = null;
        try {
            Query query = session.createQuery("from Users where username=:username and userpass=:userpass and nhomquyen=:nhomquyen");
            query.setString("username", username);
            query.setString("userpass", userpass);
            query.setString("nhomquyen", "1");
            user = query.list();
            session.getTransaction().commit();
            if (user.size() >= 1) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            check = false;
        }
        session.close();
        return check;
    }
}
