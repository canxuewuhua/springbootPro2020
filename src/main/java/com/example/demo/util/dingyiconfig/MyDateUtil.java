package com.example.demo.util.dingyiconfig;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;

public class MyDateUtil {

    @Autowired
    private UtilProperties props;

    public String getLocalTime(){
        int zone = 0;
        if (null != props.getLatitude()){
            zone = (int)Math.round((props.getLatitude() * DateTimeConstants.HOURS_PER_DAY) / 360);
        }
        DateTimeZone dz = DateTimeZone.forOffsetHours(zone);
        return new DateTime(dz).toString(props.getPattern());
    }
}
