package au.id.philipbrown.auspost.pac.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class AuthKeyHttpHeaderInterceptor implements ClientHttpRequestInterceptor {
    public static final String HEADER = "auth-key";

    private final String authKey;

    public AuthKeyHttpHeaderInterceptor(final String authKey) {
        this.authKey = authKey;
    }

    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add(HEADER, authKey);
        return execution.execute(request, body);
    }
}
