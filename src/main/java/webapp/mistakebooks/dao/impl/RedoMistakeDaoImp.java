package webapp.mistakebooks.dao.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import webapp.mistakebooks.dao.RedoMistakeDao;
import webapp.mistakebooks.model.MistakeEntity;
import webapp.mistakebooks.model.RedomistakeEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RedoMistakeDaoImp implements RedoMistakeDao {
    public String add(RedomistakeEntity redo) {
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query1 = session.createQuery("select a from MistakeEntity a " +
                "where a.mistakeId= :mistakeid").
                setParameter("mistakeid", redo.getMistakeId());
        List<MistakeEntity> list1 = query1.list();
        if (list1.size() == 0) {
            transaction.commit();
            return "ID doesn't exist!";
        } else {
            Query query = session.createQuery("select a from RedomistakeEntity a " +
                    "where a.mistakeId= :mistakeid").
                    setParameter("mistakeid", redo.getMistakeId());
            List<MistakeEntity> list = query.list();

            int listLength = list.size();
            if (listLength == 0) {
                redo.setTimes(1);
            } else {
                redo.setTimes(listLength + 1);
            }
            redo.setRedoDate(null);
            System.out.println("user: " + redo.getUserId());
            session.save(redo);
            transaction.commit();
            return "success";
        }
    }

    public JSONArray search(int mistakeID) {
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select p from RedomistakeEntity as p where p.mistakeId= :id").
                setParameter("id", mistakeID);
        List<MistakeEntity> list = query.list();
        JSONArray jsonArray = new JSONArray();
        if (list.size() > 0) {
            if (jsonArray.addAll(list)) {
                transaction.commit();
                return jsonArray;
            }
        }
        return null;
    }

    public JSONArray listRedoAmount(String userID) {
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query1 = session.createQuery("select p from MistakeEntity as p where p.userId= :id").
                setParameter("id", userID);
        Query query2 = session.createQuery("select p from RedomistakeEntity as p where p.userId= :id").
                setParameter("id", userID);
        List<RedomistakeEntity> list2 = query2.list();
        List<MistakeEntity> list3 = query1.list();


        JSONArray jsonArray = new JSONArray();
        List<Integer> list1 = new ArrayList<Integer>();

        if (list3.size() > 0) {
            for (int i = 0; i < list3.size(); i++) {
                int amount = 0;
                int mistakeID = list3.get(i).getMistakeId();
                for (int j = 0; j < list2.size(); j++) {
                    if (mistakeID == (list2.get(j).getMistakeId())) {
                        amount++;
                    }
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("mistakeID", mistakeID);
                jsonObject.put("redoAmount", amount);
                jsonArray.add(jsonObject);
            }
        }

        System.out.println("jason: " + jsonArray);
        return jsonArray;
    }

}


