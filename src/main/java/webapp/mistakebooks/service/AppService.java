package webapp.mistakebooks.service;

import com.mongodb.gridfs.GridFSDBFile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import webapp.mistakebooks.model.MistakeEntity;
import webapp.mistakebooks.model.RedomistakeEntity;
import webapp.mistakebooks.model.UserEntity;

import java.io.File;


@Service
public interface AppService {
    public Integer addUser(UserEntity user);
    public String queryUser(UserEntity user);
    public String queryManager(UserEntity user);
    public String deleteUser(String name);
    public String forbiddenUser(String name);
    public String recoverUser(String name);
    public JSONArray listUser();

    public JSONArray listMistake(String userId);
    public String addMistake(MistakeEntity mistake);
    public String deleteMistake(int mistakeID);
    public JSONObject searchMistake(int mistakeID);
    public String queryTagtip(String subject);
    public Integer amountMistake();
    public JSONArray listUserMistakeAmount();
    public JSONArray listRedoAmount(String userID);
    public JSONArray listTagRelatedMistake(String tag);


    public String addRedo(RedomistakeEntity redo);
    public JSONArray searchRedo(int mistakeID);

    public JSONArray firstPage();
    public String addPageMistake(int mistakeID);
    public String deletePageMistake(Integer mistakeID);
    public String uploadPic(File imgFile, String fileName);
    public GridFSDBFile getPic(String fileName);






}
