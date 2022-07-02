package com.zealep.ventasbackend.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String convertDateToStringFormatddMMyyyy(Date date){
        try {
            if(date!=null){
                SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
                return sf.format(date);
            }else {
                return null;
            }
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }

        }
}
