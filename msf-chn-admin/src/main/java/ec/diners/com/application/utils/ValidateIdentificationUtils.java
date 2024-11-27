package ec.diners.com.application.utils;

import java.util.Arrays;

public class ValidateIdentificationUtils {
    public static boolean ValidateCi(String ci)
    {
        if (ci == null || ci.length() != 10)
        {
            return false;
        }

        var validateIdentification = ci.trim().toCharArray();

        var province = Integer.valueOf(validateIdentification[0] + validateIdentification[1]);
        if (province < 1 || province > 24)
        {
            return false;
        }

        var thirdDigit = 6;
        var typePerson = Integer.valueOf(validateIdentification[2]);
        if (typePerson > thirdDigit)
        {
            return false;
        }

        var checkingDigit = Integer.valueOf(validateIdentification[9]);
        var coefficients = Arrays.asList(2, 1, 2, 1, 2, 1, 2, 1, 2).toArray();

        var sum = 0;

        for (var i = 0; i < coefficients.length; i++)
        {
            var value = (int)coefficients[i] * (int)validateIdentification[i];

            sum = value > 9 ? sum + (value - 9) : sum + value;
        }

        var checkingDigitGet = (sum >= 10)
                ? (sum % 10) != 0
                ? 10 - (sum % 10)
                : (sum % 10)
                : sum;

        if (checkingDigitGet != checkingDigit)
        {
            return false;
        }

        return true;
    }

    public static boolean ValidateRuc(String ruc)
    {
        if (ruc == null || ruc.length() != 13)
        {
            return false;
        }

        var validateIdentification = ruc.trim().toCharArray();

        var province = Integer.valueOf(validateIdentification[0] + validateIdentification[1]);
        if (province < 1 || province > 24)
        {
            return false;
        }

        var typePerson = Integer.valueOf(validateIdentification[2]);
        if (typePerson > 6 && typePerson != 9)
        {
            return false;
        }

        var rucArray = ruc.toCharArray();
        if (String.valueOf(rucArray[10] + rucArray[11] + rucArray[12]).equals("000"))
        {
            return false;
        }

        var checkingDigit = 0;
        Object[] coefficients;
        if (typePerson < 6)
        {
            checkingDigit = (int) validateIdentification[9];
            coefficients = Arrays.asList(2, 1, 2, 1, 2, 1, 2, 1, 2).toArray();

            var sum = 0;
            for (var i = 0; i < coefficients.length; i++)
            {
                var value = (int)coefficients[i] * (int) validateIdentification[i];
                if (value > 9)
                {
                    sum += value % 10 + 1;
                }
                else
                {
                    sum += value;
                }
            }

            var residue = sum % 10;
            if (residue == 0 && checkingDigit == 0)
            {
                return true;
            }

            if (10 - sum % 10 != checkingDigit)
            {
                return false;
            }

        }
        else
        {
            if (typePerson == 6){
                checkingDigit = (int) validateIdentification[8];
                coefficients = Arrays.asList(3, 2, 7, 6, 5, 4, 3, 2).toArray();
            }
            else{
                checkingDigit = (int)validateIdentification[9];
                coefficients = Arrays.asList(4, 3, 2, 7, 6, 5, 4, 3, 2).toArray();
            }

            var sum = 0;
            for (var i = 0; i < coefficients.length; i++)
            {
                var value = (int)coefficients[i] * (int)validateIdentification[i];
                sum += value;
            }

            var residue = sum % 11;

            if (residue == 0 && checkingDigit == 0)
            {
                return true;
            }

            if (11 - sum % 11 != checkingDigit)
            {
                return false;
            }
        }

        return true;
    }
}
