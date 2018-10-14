package com.aburubban.halalfoodfinder.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.aburubban.halalfoodfinder.Model.User;
import com.aburubban.halalfoodfinder.Remote.APIService;
import com.aburubban.halalfoodfinder.Remote.RetrofitClient;

/**
 * Created by Abu Rubban on 23/2/2561.
 */

public class Common {
    public static User currentUser;

    private static final String BASE_URL = "https://fcm.googleapis.com/";

    public static APIService getFCMService()
    {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public static String convertCodeToStatus(String status) {
        if(status.equals("0"))
            return "รอการอัพเดทสถานะ";
        else if(status.equals("1"))
            return "รับออเดอร์";
        else if (status.equals("2"))
            return "อยู่ระหว่างการส่ง";
        else
            return "จ่ายเงินเรียบร้อย";

    }


    public static final String DELETE = "Delete";
    public static final String USER_KEY = "User";
    public static final String PWD_KEY = "Password";


    public static boolean isConnectedToInterner(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null)
            {
                for (int i=0;i<info.length;i++)
                {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }

}
