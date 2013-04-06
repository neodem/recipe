package com.neodem.recipe.db.hibernate.dao;

import java.util.Collection;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate.HibernateCallback;
import org.springframework.orm.hibernate.HibernateTemplate;

import com.neodem.recipe.db.beans.Recipe;
import com.neodem.recipe.db.dao.RecipeDAO;

public class HibernateRecipeDAO extends HibernateBaseDAO implements RecipeDAO {
    protected static Log _log = LogFactory.getLog(HibernateRecipeDAO.class.getName());
    

	public Collection findRecipeLikeName(final String searchName) {
        HibernateTemplate ht = getHibernateTemplate();
        List recipes = (List)ht.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException {
                Query q = session.getNamedQuery("QueryGetAllRecipesLikeName");
                q.setString("searchName", searchName);
                return q.list();
            }
        });
        
        
        return recipes;
	}

	public void removeRecipeByID(Long id) {
		Object recipe = getHibernateTemplate().load(Recipe.class, id);
		getHibernateTemplate().delete(recipe);

	}

	public Recipe getRecipeByID(Long id) {
		return (Recipe) getHibernateTemplate().get(Recipe.class, id);
	}



}
