package com.neodem.recipe.db.hibernate.dao;


import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate.HibernateCallback;
import org.springframework.orm.hibernate.HibernateTemplate;

import com.neodem.recipe.db.beans.User;
import com.neodem.recipe.db.dao.UserDAO;

public class HibernateUserDAO extends HibernateBaseDAO implements UserDAO {
    protected static Log _log = LogFactory.getLog(HibernateRecipeDAO.class.getName());

	public User findByUsername(final String username) {

        HibernateTemplate ht = getHibernateTemplate();
        List users = (List)ht.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException {
                Query q = session.getNamedQuery("QueryGetAllUserRecordsByUsername");
                q.setString("username", username);
                return q.list();
            }
        });
        
        if((users == null) || (users.size() != 1)) return null;
        
        return (User)users.get(0);

    }
}
    