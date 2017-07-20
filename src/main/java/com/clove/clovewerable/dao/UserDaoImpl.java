package com.clove.clovewerable.dao;

import com.clove.clovewerable.User;
import com.clove.clovewerable.dao.entity.RegistrationExpiry;
import com.clove.clovewerable.dao.entity.UserDetails;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author @Unascribed
 */
@Service
public class UserDaoImpl implements UserDao, Serializable {

    private static final long serialVersionUID = 8487221148681495588L;

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public boolean doesLoginIdExist(String loginId) {

        String hql = "from UserDetails where loginId= :loginId";
        Query q = sessionFactory.getCurrentSession().createQuery(hql);
        q.setParameter("loginId", loginId);

        return !q.list().isEmpty();
    }

    @Override
    public int registerUser(User user) {

        UserDetails ud = new UserDetails();
        ud.setLoginId(user.getLoginId());
        ud.setEmail(user.getEmail());
        ud.setName(user.getName());
        ud.setPincode(user.getPincode());
        ud.setCreatedBy("TESTS");
        ud.setCreatedDateTime(DateTime.now().toDate());

        sessionFactory.getCurrentSession().save(ud);

        return ud.getId();
    }

    @Override
    public User getRegisteredUser(String userLoginId) {

        String hql = "from UserDetails where loginId= :loginId";
        Query q = sessionFactory.getCurrentSession().createQuery(hql);
        q.setParameter("loginId", userLoginId);

        UserDetails entity = (UserDetails) q.uniqueResult();

        User ud = new User();
        if (null != entity) {
            ud.setLoginId(entity.getLoginId());
            ud.setEmail(entity.getEmail());
            ud.setName(entity.getName());
            ud.setPincode(entity.getPincode());
        }

        return ud;
    }

    @Override
    public User createRegUserExpirationLink(String userLoginId) {

        RegistrationExpiry exp = new RegistrationExpiry();

        DateTime dt = DateTime.now();
        Timestamp ts = new Timestamp(dt.getMillis());

        exp.setCreatedDateTime(ts);
        exp.setExpToken(generateTokenAndSaveToDB());
        exp.setLoginId(userLoginId);

        sessionFactory.getCurrentSession().save(exp);

        User toReturn = new User();
        toReturn.setRegToken(exp.getExpToken());
        toReturn.setLoginId(userLoginId);

        return toReturn;
    }

    @Override
    public User IsUserLinkExpired(String expToken) {

        String hql = "from RegistrationExpiry where expToken = :expToken";
        Query q = sessionFactory.getCurrentSession().createQuery(hql);
        q.setParameter("expToken", expToken);

        RegistrationExpiry entity = (RegistrationExpiry) q.uniqueResult();
        if (null == entity) {
            return null;
        }

        DateTime expTime = new DateTime(entity.getCreatedDateTime());
        DateTime tocompare = expTime.plusMinutes(15);
        Timestamp tsExp = new Timestamp(tocompare.getMillis());

        DateTime dt = DateTime.now();
        Timestamp tsNow = new Timestamp(dt.getMillis());

        if (tsExp.after(tsNow)) {
            User user = getRegisteredUser(entity.getLoginId());
            return user;
        }
        return null;
    }

    private String generateTokenAndSaveToDB() {

        String expToken = UUID.randomUUID().toString();
        return expToken;
    }
}
