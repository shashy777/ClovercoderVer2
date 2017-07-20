package com.clove.clovewerable.service;

import com.clove.clovewerable.User;
import com.clove.clovewerable.dao.UserDao;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author @Unascribed
 */
@Service
public class UserServiceImpl implements Serializable, UserService {

    private static final long serialVersionUID = 1L;

    @Autowired(required = true)
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public boolean doesLoginIdExist(String loginId) {
        return userDao.doesLoginIdExist(loginId);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, readOnly = false, propagation = Propagation.NESTED)
    public int registerUser(User user) {
        return userDao.registerUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getRegisteredUser(String userLoginId) {
        return userDao.getRegisteredUser(userLoginId);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, readOnly = false, propagation = Propagation.NESTED)
    public User createRegUserExpirationLink(String userLoginId) {
        return userDao.createRegUserExpirationLink(userLoginId);
    }

    @Override
    @Transactional(readOnly = true)
    public User IsUserLinkExpired(String expToken) {
        return userDao.IsUserLinkExpired(expToken);
    }

}
