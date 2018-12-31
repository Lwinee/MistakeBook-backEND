package webapp.mistakebooks.dao.impl;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import webapp.mistakebooks.dao.MistakeDao;
import webapp.mistakebooks.model.FirstpageEntity;
import webapp.mistakebooks.model.MistakeEntity;
import webapp.mistakebooks.model.UserEntity;

import java.io.File;
import java.util.*;

@Repository
public class MistakeDaoImp implements MistakeDao {
    public JSONArray list(String userId){
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select a from MistakeEntity a where a.userId= :id").
                setParameter("id", userId);
        List<MistakeEntity> list = query.list();
        System.out.println("userId: "+userId);
        JSONArray jsonArray = new JSONArray();
        if (list.size() > 0) {
            if (jsonArray.addAll(list)) {
                //JSONObject  jsonObject = jsonArray.getJSONObject(0) ;
                //jsonArray=JSONArray.fromObject(jsonArray);
                transaction.commit();
            }
        }
        return jsonArray;
    }

    public String add(MistakeEntity mistake)
    {
        Session session = webapp.mistakebooks.HibernateUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        mistake.setMistakeDate(null);
        session.save(mistake);
        Query query = session.createQuery("select a from MistakeEntity a");
        List<MistakeEntity> list = query.list();

        Integer ID=list.get(list.size()-1).getMistakeId();
        String mistakeID=Integer.toString(ID);
        transaction.commit();
        return "success! MistakeID="+mistakeID;
    }

    public String delete(int mistakeID)
    {
        Session session = webapp.mistakebooks.HibernateUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete MistakeEntity as p where p.mistakeId= :id").
                setParameter("id", mistakeID);
        query.executeUpdate();
        transaction.commit();
        return "success";
    }

    public JSONObject search(int mistakeID)
    {
        Session session = webapp.mistakebooks.HibernateUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        Query query1 = session.createQuery("select p from MistakeEntity as p where p.mistakeId= :id").
                setParameter("id", mistakeID);
        MistakeEntity mistake = (MistakeEntity) query1.uniqueResult();
        JSONObject jsonObject = JSONObject.fromObject(mistake);
        transaction.commit();
        return jsonObject;
    }

    public String searchTagtip(String subject)
    {
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select p from MistakeEntity as p where p.subject = :subject").
                setParameter("subject", subject);
        List<MistakeEntity> list = query.list();
        String uniqueTag="";
        Set<String> set = new HashSet();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String ele= list.get(i).getTag();
                String[] array = ele.split(" ");
                Set<String> staffsSet = new HashSet(Arrays.asList(array));
                set.addAll(staffsSet);
            }
        }
        int i=0;
        Iterator<String> value = set.iterator();
        while (value.hasNext()) {
            i++;
            String s = value.next();
            System.out.println("s: "+ s);
            if (i==(set.size())) {
                uniqueTag = uniqueTag +s;
            }
            else {
                uniqueTag = uniqueTag + s + " ";
            }
        }
        transaction.commit();
        return uniqueTag;
    }

    public Integer amountAll()
    {
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select a from MistakeEntity a" );
        List<MistakeEntity> list = query.list();
        Integer size=list.size();
        transaction.commit();
        return size;
    }

    public JSONArray listUserMistakeAmount()
    {
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query1 = session.createQuery("select a from UserEntity a" );
        List<UserEntity> list1 = query1.list();
        Query query2 = session.createQuery("select a from MistakeEntity a" );
        List<MistakeEntity> list2 = query2.list();
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<list1.size();i++){
            String username=list1.get(i).getId();
            Integer amount=0;
            for(int j=0;j<list2.size();j++){
                if(username.equals(list2.get(j).getUserId())){
                    amount++;
                }
            }
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("name",username);
            jsonObject.put("amount",amount);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public JSONArray listTagRelatedMistake(String tag)
    {
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select p from MistakeEntity p");
        List<MistakeEntity> list = query.list();
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<list.size();i++){
            String mistakeTag=list.get(i).getTag();
            if (mistakeTag.contains(tag)){
                JSONObject jsonObject=new JSONObject();
                System.out.println("ID:"+list.get(i).getMistakeId());

                jsonObject.put("mistakeID",list.get(i).getMistakeId());
                jsonObject.put("title",list.get(i).getMistakeTitle());
                jsonObject.put("tag",mistakeTag);
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;

    }

    public JSONArray firstPage()
    {
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select a from FirstpageEntity a ");
        List<MistakeEntity> list = query.list();
        JSONArray jsonArray = new JSONArray();
        if (list.size() > 0) {
            if (jsonArray.addAll(list)) {
                transaction.commit();
            }
        }
        return jsonArray;
    }

    public String addPageMistake(Integer mistakeID) {
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select a from MistakeEntity a where a.mistakeId= :id").
                setParameter("id", mistakeID);
        List<MistakeEntity> list = query.list();
        if (list.size() == 0) {
            transaction.commit();
            return "不存在此错题";
        } else {
            MistakeEntity mistake = (MistakeEntity) query.uniqueResult();
            FirstpageEntity add = new FirstpageEntity(mistakeID, mistake.getMistakeTitle(),
                    mistake.getMistakeCause(), mistake.getUserId(),
                    mistake.getSubject(), mistake.getTag());
            session.save(add);
            transaction.commit();
            return "success";
        }
    }

        public String deletePageMistake(Integer mistakeID)
        {
            Session session = webapp.mistakebooks.HibernateUtil
                    .getSessionFactory()
                    .openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete FirstpageEntity as p where p.mistakeId= :id").
                    setParameter("id", mistakeID);
            query.executeUpdate();
            transaction.commit();
            return "success";
        }

    public String uploadPic(File imgFile, String fileName) {
        MongoClient mongo = new MongoClient();
        DB mongodb = mongo.getDB("Mistake");
        try {
            GridFS gfsPhoto = new GridFS(mongodb, "Images");
            GridFSInputFile gfsFile = gfsPhoto.createFile(imgFile);
            gfsFile.setFilename(fileName);
            gfsFile.save();
            DBCursor cursor = gfsPhoto.getFileList();
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            GridFSDBFile imageForOutput = gfsPhoto.findOne(fileName);
            imageForOutput.writeTo("C:\\webImgSave\\newImage.png");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
            return "Done";

        }
    }

    public GridFSDBFile getPic(String fileName)
    {
        MongoClient mongo = new MongoClient();
        DB mongodb = mongo.getDB("Mistake");

            GridFS gfsPhoto = new GridFS(mongodb, "Images");
            // get image file by it's filename
            GridFSDBFile imageForOutput = gfsPhoto.findOne(fileName);
            mongo.close();



        return imageForOutput;
    }




    }


