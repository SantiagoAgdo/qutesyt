package ec.diners.com.domain.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class UtilDates {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final ZoneId ZONE_ID = ZoneId.of("America/Guayaquil");

    public static LocalDateTime converterStringToLocalDateTimeBegin(String date) {
        return LocalDateTime.parse(date + " 00:00:00", dtf);
    }




    public static LocalDateTime converterStringToLocalDateTimeEnd(String date) {
        return LocalDateTime.parse(date + " 23:59:59", dtf);
    }

    public static Date getNowTodayDate() {
        // Obtener el tiempo actual como LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();

        // Convertir LocalDateTime a ZonedDateTime con la zona horaria especificada
        Instant instant = localDateTime.atZone(ZONE_ID).toInstant();

        // Convertir Instant a java.util.Date
        return Date.from(instant);
    }

    public static LocalDateTime converterLocalDateToLocalDateTimeBegin(LocalDate date) {
        return date.atStartOfDay();
    }

    public static LocalDateTime converterLocalDateToLocalDateTimeEnd(LocalDate date) {
        return date.atTime(23,59, 59);
    }

    public static LocalDateTime getCurrentDate() {
        return LocalDateTime.now(ZoneId.of(Constants.ZONA_LOCAL));
    }


    public static String getDateFormatFromString(Date dateItem){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(dateItem);
    }

    public static String getDateFormatFromStringDate(Date dateItem){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(dateItem);
    }

}
