package cl.metlife.documentsender.utils;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static Logger logger = Logger.getLogger(DateUtils.class);

    public static Date getCurrentDateAtZeroOclock() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date parseDate(String proposedDate) throws ParseException {

        String[] formats = {"dd/MM/yyyy", "dd-MM-yyyy", "dd/MMM/yyyy", "dd-MMM-yyyy", "yyyy-MM-dd", "yyyy/MM/dd"};
        SimpleDateFormat simpleDateFormat;
        Date parse;

        for (String format : formats) {
            simpleDateFormat = new SimpleDateFormat(format);

            try {
                parse = simpleDateFormat.parse(proposedDate);
                /* logger.info("Date parsed in format "+format+": " + parse); */
                return parse;
            } catch (ParseException e) {
                logger.trace("The date was not in the format " + format);
            }
        }

        throw new ParseException("The date was not parseable to any specified format.", 0);
    }
}