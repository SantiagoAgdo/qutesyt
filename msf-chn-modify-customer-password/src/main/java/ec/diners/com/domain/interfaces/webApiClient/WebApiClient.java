package ec.diners.com.domain.interfaces.webApiClient;

import java.util.List;

public interface WebApiClient {
    String get(String urlString, List<String> headers);
    String get(String urlString);
    String post(String urlString, String arguments, List<String> headers);
    String post(String urlString, String arguments);
    String put(String urlString, String arguments, List<String> headers);
    String put(String urlString, String arguments);
    String patch(String urlString, String arguments, List<String> headers);
    String patch(String urlString, String arguments);
}
