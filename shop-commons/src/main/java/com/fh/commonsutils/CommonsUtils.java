package com.fh.commonsutils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonsUtils {



   /* public static UserBean InforSession(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return (UserBean)session.getAttribute("user");
    }*/

    public static String getStringDate(Date date){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }


}
