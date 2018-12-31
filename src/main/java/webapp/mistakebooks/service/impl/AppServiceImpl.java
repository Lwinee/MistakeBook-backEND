package webapp.mistakebooks.service.impl;

import com.mongodb.gridfs.GridFSDBFile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.mistakebooks.dao.FirstPageDao;
import webapp.mistakebooks.dao.MistakeDao;
import webapp.mistakebooks.dao.RedoMistakeDao;
import webapp.mistakebooks.dao.UserDao;
import webapp.mistakebooks.model.MistakeEntity;
import webapp.mistakebooks.model.RedomistakeEntity;
import webapp.mistakebooks.model.UserEntity;
import webapp.mistakebooks.service.AppService;

import java.io.File;

@Service
public class AppServiceImpl implements AppService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MistakeDao mistakeDao;
    @Autowired
    private RedoMistakeDao redoMistakeDao;
    //@Autowired
    //private FirstPageDao firstPageDao;




    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public void setMistakeDao(MistakeDao mistakeDao) {
        this.mistakeDao = mistakeDao;
    }


    public Integer addUser(UserEntity user) {
        return userDao.save(user);
    }
    public String queryUser(UserEntity user) {
        return userDao.query(user);
    }
    public String queryManager(UserEntity user){return userDao.queryManager(user);}
    public String deleteUser(String name) {
        return userDao.delete(name);
    }
    public String forbiddenUser(String name) {
        return userDao.forbidden(name);
    }
    public String recoverUser(String name){return userDao.recover(name);}
    public JSONArray listUser(){return userDao.list();}

    public JSONArray listMistake(String userId){
        return mistakeDao.list(userId);
    }
    public String addMistake(MistakeEntity mistake){
        return mistakeDao.add(mistake);
    }
    public String deleteMistake(int mistakeID){
        return mistakeDao.delete(mistakeID);
    }
    public JSONObject searchMistake(int mistakeID){
        return mistakeDao.search(mistakeID);
    }
    public String queryTagtip(String subject){
        return mistakeDao.searchTagtip(subject);
    }
    public Integer amountMistake(){
        return mistakeDao.amountAll();
    }
    public JSONArray listUserMistakeAmount(){return mistakeDao.listUserMistakeAmount();}
    public JSONArray listTagRelatedMistake(String tag){return mistakeDao.listTagRelatedMistake(tag);}
    public String uploadPic(File imgFile, String fileName){return mistakeDao.uploadPic(imgFile,fileName);}
    public GridFSDBFile getPic(String fileName){return mistakeDao.getPic(fileName);}

    public JSONArray listRedoAmount(String userID){return redoMistakeDao.listRedoAmount(userID);}
    public String addRedo(RedomistakeEntity redo){
        return redoMistakeDao.add(redo);
    }
    public JSONArray searchRedo(int mistakeID){
        return redoMistakeDao.search(mistakeID);
    }

    public JSONArray firstPage(){return mistakeDao.firstPage();}
    public String addPageMistake(int mistakeID){return mistakeDao.addPageMistake(mistakeID);}
    public String deletePageMistake(Integer mistakeID){return mistakeDao.deletePageMistake(mistakeID);}
}
