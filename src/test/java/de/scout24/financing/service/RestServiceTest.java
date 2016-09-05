package de.scout24.financing.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import de.scout24.financing.service.impl.RestService;

@RunWith(MockitoJUnitRunner.class)
public class RestServiceTest {

    @Mock
    private RestService restService;
    
    @Before
    public void setup() {
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testExchange() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("key", "value");
        ResponseEntity<List<String>> mockedResponseEntity = new ResponseEntity<List<String>>(headers, HttpStatus.OK);
        Mockito.when(restService.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(Class.class), 
                Mockito.any(MultiValueMap.class), Mockito.any(ParameterizedTypeReference.class))).thenReturn(mockedResponseEntity);
        ResponseEntity<List<String>> responseEntity = restService.exchange("http://test.de", HttpMethod.GET, String.class, headers, 
                new ParameterizedTypeReference<List<String>>() {});
        Assert.assertTrue(responseEntity.getHeaders().containsKey("key"));
    }

}
