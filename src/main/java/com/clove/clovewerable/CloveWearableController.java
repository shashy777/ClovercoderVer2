package com.clove.clovewerable;

import com.clove.clovewerable.mail.MailService;
import com.clove.clovewerable.service.UserService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author unascribed
 */
@Controller(value = "CloveWearableController")
public class CloveWearableController implements Serializable {

    private static final long serialVersionUID = -7316811822357681808L;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    /**
     * Register User Service
     *
     * @param user
     * @return
     */
    @RequestMapping(value = ClovewearableRestURIConstants.CREATE_REG, method = RequestMethod.POST)
    public @ResponseBody
    RegistrationResultBean registerUser(@RequestBody User user) {

        RegistrationResultBean toReturn = new RegistrationResultBean();

        if (null == user.getLoginId() || user.getLoginId().trim().equalsIgnoreCase("null")) {
            toReturn.setResult("FAILURE, User Id Required !");
            return toReturn;
        }

        if (!userService.doesLoginIdExist(user.getLoginId())) {
            userService.registerUser(user);
            toReturn.setResult("SUCESS -- User " + user.getName() + " was Registered");
        } else {
            toReturn.setResult("FAILURE -- User Id " + user.getLoginId() + " not available");
        }

        return toReturn;
    }

    /**
     * Fetch registered User by loginId Service
     *
     * @param userLoginId
     * @return
     */
    @RequestMapping(value = ClovewearableRestURIConstants.GET_REG_USER, method = RequestMethod.GET)
    public @ResponseBody
    User getRegisteredUser(@PathVariable("loginid") String userLoginId) {

        User user = new User();
        if (userService.doesLoginIdExist(userLoginId)) {
            user = userService.createRegUserExpirationLink(userLoginId);

            mailService.sendEmail(user.getName(), user.getRegToken());
            user.setMessage("LOGIN LINK SENT TO REGISTERED EMAIL, PLEASE CHECK");
        } else {
            user.setLoginId("");
            user.setMessage("FAILURE : COULD NOT FIND USER !");
        }

        return user;
    }

    /**
     * Check link expiration service
     *
     * @param expToken
     * @return
     */
    @RequestMapping(value = ClovewearableRestURIConstants.VALIDATE_REG_USER, method = RequestMethod.GET)
    public @ResponseBody
    User checkUserRegExpirationLink(@RequestParam("expToken") String expToken) {

        User toReturn = userService.IsUserLinkExpired(expToken);
        if (null == toReturn) {
            toReturn = new User();
            toReturn.setMessage(" FAILURE, ***Link has Expired*** ");
        }
        return toReturn;
    }
}
