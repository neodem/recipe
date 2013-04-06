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

import com.neodem.recipe.db.beans.Ingredient;
import com.neodem.recipe.db.dao.IngredientDAO;

public class HibernateIngredientDAO extends HibernateBaseDAO implements IngredientDAO {
	protected static Log _log = LogFactory.getLog(HibernateIngredientDAO.class.getName());

	public Collection findRecipeLikeName(final String name) {
		HibernateTemplate ht = getHibernateTemplate();
		List result = (List) ht.execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException {
				Query q = session.getNamedQuery("QueryGetAllLikeName");
				q.setString("searchName", name);
				return q.list();
			}
		});

		return result;
	}

	public void removeIngredientByID(Long id) {
		Object ingredient = getHibernateTemplate().load(Ingredient.class, id);
		getHibernateTemplate().delete(ingredient);

	}

	public Ingredient getIngredientByID(Long id) {
		return (Ingredient) getHibernateTemplate().get(Ingredient.class, id);
	}
}
