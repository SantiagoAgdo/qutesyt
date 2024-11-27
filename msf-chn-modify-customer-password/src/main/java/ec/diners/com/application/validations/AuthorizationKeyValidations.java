package ec.diners.com.application.validations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuthorizationKeyValidations {

    private AuthorizationKeyValidations() {

    }

    // Método para validar la clave de autorización
    public static boolean authorizationKeyValidation(String authorizationKey, String rucFormulario, Date emissionDate) {
        try {

            // Extraer la fecha y el RUC de la clave de autorización
            String fechaAutorizacion = authorizationKey.substring(0, 8); // Primeros 8 caracteres
            String rucAutorizacion = authorizationKey.substring(10, 23); // A partir del caracter 10, el RUC tiene 13 dígitos

            // Convertir la fecha de la clave de autorización a formato dd/MM/yyyy
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            Date fechaAutorizacionDate = sdf.parse(fechaAutorizacion);

            // Comparar fecha y RUC
            return fechaAutorizacionDate.equals(emissionDate) && rucAutorizacion.equals(rucFormulario);

        } catch (ParseException e) {
            return false;
        }
    }

}
