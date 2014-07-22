/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acsvrp.tools;

import java.util.Calendar;

/**
 * Class has only one method which returns formated time [now] as string: [dd_MM_YYYY_hh_MM_ss].
 * @author Milos Panasiuk
 */
public class CalendarTime {
    
    public static final String getFormatedTime()
    {
        Calendar calendar = Calendar.getInstance();
            
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        String timeNow = day + "_" + month + "_" + year + "_" + hours + "_" + minutes + "_" + seconds;

        return timeNow;
    }
}
