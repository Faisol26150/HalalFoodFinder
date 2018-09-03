package com.aburubban.halalfoodfinder.Common;

import com.aburubban.halalfoodfinder.Model.User;

/**
 * Created by Abu Rubban on 23/2/2561.
 */

public class Common {
    public static User currentUser;

    public static String convertCodeToStatus(String status) {
        if(status.equals("0"))
            return "Placed";
        else if(status.equals("1"))
            return "On my way";
        else
            return "Shipped";

    }

    public static final String USER_KEY = "User";
    public static final String PWD_KEY = "Password";
}
