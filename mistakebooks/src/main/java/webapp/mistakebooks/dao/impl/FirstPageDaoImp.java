package webapp.mistakebooks.dao.impl;

import net.sf.json.JSONArray;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import webapp.mistakebooks.action.FirstPage;
import webapp.mistakebooks.model.FirstpageEntity;
import webapp.mistakebooks.model.MistakeEntity;
import java.util.List;

@Repository
public class FirstPageDaoImp {
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

    public String add(Integer mistakeID) {
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
            FirstpageEntity add = new FirstpageEntity(mistakeID,mistake.getMistakeTitle(),
                    mistake.getMistakeCause(),mistake.getUserId(),
                    mistake.getSubject(),mistake.getTag());
            session.save(add);
            transaction.commit();
            return "success";
        }


    }

}
