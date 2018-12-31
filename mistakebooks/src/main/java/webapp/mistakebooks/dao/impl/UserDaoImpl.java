package webapp.mistakebooks.dao.impl;

import net.sf.json.JSONArray;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import webapp.mistakebooks.dao.UserDao;
import webapp.mistakebooks.model.UserEntity;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    public Integer save(UserEntity user) {
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select a from UserEntity a where a.id= :id").
                setParameter("id", user.getId());
        List<UserEntity> list = query.list();
        if (list.size() == 0) {
            session.save(user);
            transaction.commit();
            return 1;
        } else {
            return 0;
        }
    }

    public String query(UserEntity user) {
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select a " +
                "from UserEntity a " +
                "where a.id= :id").
                setParameter("id", user.getId());
        List<UserEntity> list = query.list();
        if (list.size() == 0) {
            transaction.commit();
            return "用户不存在";
        } else {
            UserEntity queryUser = (UserEntity) query.uniqueResult();
            String password = queryUser.getPassword();
            Byte role=queryUser.getRole();
            if(role==1)
            {
                transaction.commit();
                return "请前往管理员登录界面";
            }
            if (!user.getPassword().equals(password)) {
                transaction.commit();
                return "密码错误";
            } else {
                transaction.commit();
                return "登陆成功";
            }
        }
    }

    public String queryManager(UserEntity user)
    {
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select a " +
                "from UserEntity a " +
                "where a.id= :id").
                setParameter("id", user.getId());
        List<UserEntity> list = query.list();
        if (list.size() == 0) {
            transaction.commit();
            return "用户不存在";
        } else {
            UserEntity queryUser = (UserEntity) query.uniqueResult();
            String password = queryUser.getPassword();
            System.out.println("enter: "+user.getPassword());
            System.out.println("psw: "+password);
            Byte role=queryUser.getRole();
            Byte valid=queryUser.getValid();
            if(role==0)
            {
                transaction.commit();
                return "请前往用户登录界面";
            }
            if (valid==0) {
                transaction.commit();
                return "你已被封禁";
            }
            if (!user.getPassword().equals(password)) {
                transaction.commit();
                return "密码错误";
            } else {
                transaction.commit();
                return "登陆成功";
            }
        }
    }

    public String delete(String name) {
        Session session = webapp.mistakebooks.HibernateUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete UserEntity as p where p.id= :id").
                setParameter("id", name);
        query.executeUpdate();
        transaction.commit();
        return "success";
    }

    public String forbidden(String name) {
        Session session = webapp.mistakebooks.HibernateUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select a from UserEntity a where a.id= :id").
                setParameter("id", name);
        List<UserEntity> list = query.list();
        UserEntity user = list.get(0);
        Byte valid = 0;
        user.setValid(valid);
        transaction.commit();
        return "success";
    }

    public String recover(String name) {
        Session session = webapp.mistakebooks.HibernateUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select a from UserEntity a where a.id= :id").
                setParameter("id", name);
        List<UserEntity> list = query.list();
        UserEntity user = list.get(0);
        Byte valid = 1;
        user.setValid(valid);
        transaction.commit();
        return "success";
    }
    public JSONArray list()
    {
        Session session = webapp.mistakebooks.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select a from UserEntity a" );
        List<UserEntity> list = query.list();
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
}





