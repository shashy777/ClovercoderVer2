package com.clove.clovewerable.service;

import com.clove.clovewerable.User;

/**
 *
 * @author @Unascribed
 */
public interface UserService {

    public boolean doesLoginIdExist(String loginId);

    public int registerUser(User user);

    public User getRegisteredUser(String userLoginId);

    public User createRegUserExpirationLink(String userLoginId);

    public User IsUserLinkExpired(String expToken);
}
