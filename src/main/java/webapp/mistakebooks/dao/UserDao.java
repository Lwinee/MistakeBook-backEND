package webapp.mistakebooks.dao;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Repository;
import webapp.mistakebooks.model.UserEntity;

@Repository
public interface UserDao {
    public Integer save(UserEntity user);
    public String query(UserEntity user);
    public String queryManager(UserEntity user);
    public String delete(String name);
    public String forbidden(String name);
    public String recover(String name);
    public JSONArray list();

}
