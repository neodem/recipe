package com.neodem.recipe.db.common;
import java.util.List;

public interface BaseDAO {
    public BaseVO findByID(Class clazz, Long id);
    
    public void save(BaseVO vo); 
    public void insert(BaseVO vo);    
    public void update(BaseVO vo);
    public void delete(BaseVO vo);
    public List findAll(Class clazz);
}
