package ec.diners.com.infrastructure.utils.ThemesPriority;

import ec.diners.com.domain.utils.Constants;

public class PartnerThemeUtil {

    public static Long getThemeUser(Long themeIdByUser){
        Long themeId = Constants.PARAM_DEFAUL_THEME_APP;
        //Se envia de la consulta general del Job themeId by user
        themeId = themeIdByUser != null && themeIdByUser > 0 ? themeIdByUser : themeId;
        return themeId;
    }

}
