package ec.diners.com.application.validations;

import java.util.Calendar;
import java.util.Date;

public class DateValidations {
    private DateValidations() {
    }

    public static boolean afterDateValidation(Date inputDate) {
        Date actualDate = deleteHour(new Date());
        Date inputDateWithoutHour = deleteHour(inputDate);

        // Verifica que la fecha de emisión no sea posterior a la fecha actual
        return inputDateWithoutHour.after(actualDate);
    }

    // Método para eliminar hora/minutos/segundos de la fecha
    private static Date deleteHour(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

}
