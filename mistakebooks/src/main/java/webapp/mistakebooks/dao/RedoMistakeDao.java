package webapp.mistakebooks.dao;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Repository;
import webapp.mistakebooks.model.RedomistakeEntity;

@Repository
public interface RedoMistakeDao {
    public String add(RedomistakeEntity redo);
    public JSONArray search(int mistakeID);
    public JSONArray listRedoAmount(String userID);
}

