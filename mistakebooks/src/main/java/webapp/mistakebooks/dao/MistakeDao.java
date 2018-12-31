package webapp.mistakebooks.dao;

import com.mongodb.gridfs.GridFSDBFile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Repository;
import webapp.mistakebooks.model.MistakeEntity;

import java.io.File;

@Repository
public interface MistakeDao {
    public JSONArray list(String userId);
    public String add(MistakeEntity mistake);
    public String delete(int mistakeID);
    public JSONObject search(int mistakeID);
    public String searchTagtip(String subject);
    public Integer amountAll();
    public JSONArray listUserMistakeAmount();
    public JSONArray listTagRelatedMistake(String tag);

    public JSONArray firstPage();
    public String addPageMistake(Integer mistakeID);
    public String deletePageMistake(Integer mistakeID);
    public String uploadPic(File imgFile, String fileName);
    public GridFSDBFile getPic(String fileName);
}
