package com.neodem.recipe.db.hibernate.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

import com.neodem.recipe.db.common.BaseDAO;
import com.neodem.recipe.db.common.BaseVO;

public class HibernateBaseDAO extends HibernateDaoSupport implements BaseDAO {
    protected static Log _log = LogFactory.getLog(HibernateBaseDAO.class.getName());
	
    public void save(BaseVO vo) {
		_log.debug("dao.save()");
		_log.debug(vo);
		getHibernateTemplate().saveOrUpdate(vo);
		_log.debug("dao.save() - complete");
    }

    public void update(BaseVO vo) {
        getHibernateTemplate().update(vo);
    }

    public void delete(BaseVO vo) {
        getHibernateTemplate().delete(vo);
    }

    public void insert(BaseVO vo) {
        save(vo);
    }

    public List findAll(Class clazz) {
        return getHibernateTemplate().loadAll(clazz);
    }

	public BaseVO findByID(Class clazz, Long id) {
		 return (BaseVO)getHibernateTemplate().get(clazz, id);
	}


}
