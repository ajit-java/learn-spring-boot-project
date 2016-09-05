package de.scout24.financing.service.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    private static RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate();

        // TODO for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

            public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                if (hostname.equals("exfo.sandbox-immobilienscout24.de"))
                {
                    return true;
                }
                return false;
            }
        });
    }

    public <A, B> ResponseEntity<B> exchange(String url, HttpMethod method, Class<A> requestClass, MultiValueMap<String, String> headers,
            ParameterizedTypeReference<B> responseType) throws RestClientException {
        RequestEntity<A> requestEntity;
        try {
            requestEntity = new RequestEntity<A>(headers, method, new URI(url));
            return restTemplate.exchange(requestEntity, responseType);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }

}
