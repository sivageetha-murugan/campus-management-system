package com.i2i.cms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class handles the validation of date and calculates the period differences between two dates.
 */

public final class DateUtils {
 
    private DateUtils() {}

    /**
     * <p>
     * Calculates the period difference in years between the current date and the date that is given as the input. 
     * </p>
     * 
     * @param date 
     *        The date from which the difference has to be calculated.
     *
     * @return The period difference in years. Ex: 21.
     *         If the input date is greater than the present date, it will return the difference in negative values.
     */

    public static int calculatePeriodDifference(Date date) {
        Date currentDate = new Date();
        return currentDate.getYear() - date.getYear();
    }

    /**
     * <p>
     * This method checks whether the given date is in the specified format (yyyy/mm/dd) or not.
     * </p>
     * 
     * @param inputDate 
     *        The data which is to be validated.
     *        The input date should be in the format of yyyy/mm/dd.
     *
     * @return A Date if the inputDate is in the correct format (yyyy/MM/dd).
     *         Null if the format is not correct.
     */

    public static Date validateDate(String inputDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try { 
            return simpleDateFormat.parse(inputDate);
        } catch (ParseException e) {
            return null;
        }
    }

}
