package example;

import com.lyve.service.AbsractServicesBaseClass;
import org.apache.log4j.Logger;

/**
 * Created by mmadhusoodan on 3/5/15.
 */
public class GetDeviceLastSeen extends AbsractServicesBaseClass {
    private static Logger log = Logger.getLogger(GetDeviceLastSeen.class);


//    public static String getDeviceLastSeen(long epochSec) {
//        try {
//            DecimalFormat lastSeenFormater = new DecimalFormat("###,###");
//            DateFormat sdf = new SimpleDateFormat(AbsractServicesBaseClass.DATEFORMAT);
//
//            String date1 = AbsractServicesBaseClass.getHumanReadableDateFromEpoch(epochSec);
//            String date2 = AbsractServicesBaseClass.getCurrentDateAndTime();
//
//            log.info("date1: " + date1);
//            log.info("date2: " + date2);
//
//            Date dateObj1 = sdf.parse(date1);
//            Date dateObj2 = sdf.parse(date2);
//
//            long lastSeenDiff = dateObj2.getTime() - dateObj1.getTime();
//
//            int diffhours = (int) (lastSeenDiff / (60 * 60 * 1000));
//            log.info("difference between hours: " + lastSeenFormater.format(diffhours));
//
//            int diffDays = (int) (lastSeenDiff / (24 * 60 * 60 * 1000));
//            log.info("difference between days: " + diffDays);
//
//            int diffWeeks = (int) (lastSeenDiff / (7 * 24 * 60 * 60 * 1000));
//            log.info("difference between weeks: " + diffWeeks);
//
//            int diffMonth = (int) (lastSeenDiff / (7 * 4 * 24 * 60 * 60 * 1000));
//            log.info("difference between months: " + diffMonth);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Date date = new Date(epochSec);
//        SimpleDateFormat format = new SimpleDateFormat(DATEFORMAT, Locale.getDefault());
//
//        return format.format(date);
//    }


    public static void main(String[] args) {

        log.info("LAST SEEN: " + AbsractServicesBaseClass.getDeviceLastSeen(1424736346924l));

        log.info(" " + (long) Math.ceil(10.791666666666666));


    }
}
