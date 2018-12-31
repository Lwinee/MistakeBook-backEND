package webapp.mistakebooks.dao;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Repository;


@Repository
public interface FirstPageDao {
    public JSONArray firstPage();
    public String add(Integer mistakeID);
}
