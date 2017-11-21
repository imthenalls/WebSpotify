/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao.implementation;
import java.util.List;
import com.team0n3.webspotify.dao.UserDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import com.team0n3.webspotify.model.User;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author JSCHA
 */
public class UserDAOHibernateImpl implements UserDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    public UserDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public User getUser(String username) {
        return (User)sessionFactory.getCurrentSession().get(User.class, username);
    }

    @Override
    public List<User> listUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public User findByEmail(String email){
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(User.class);
        cr.add(Restrictions.eq("email", email));
        List results = cr.list();
        if(results.size()==0){
            return null;
        }
        User user = (User) results.get(0);
        return user;
    }
    
}
