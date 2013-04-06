package com.neodem.recipe.db.dao;

import com.neodem.recipe.db.beans.User;
import com.neodem.recipe.db.common.BaseDAO;

public interface UserDAO extends BaseDAO {

	public User findByUsername(String username);
	
}
