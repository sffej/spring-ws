package com.codenotfound.ws.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

import com.codenotfound.ws.interceptor.CustomClientInterceptor;

@Configuration
public class ClientConfig {

  @Bean
  Jaxb2Marshaller jaxb2Marshaller() {

    Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
    jaxb2Marshaller.setContextPath("org.example.ticketagent");
    return jaxb2Marshaller;
  }

  @Bean
  public WebServiceTemplate webServiceTemplate() {

    WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
    webServiceTemplate.setMarshaller(jaxb2Marshaller());
    webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
    webServiceTemplate.setDefaultUri("http://localhost:9090/codenotfound/ws/ticketagent");

    // register the CustomEndpointInterceptor
    ClientInterceptor[] interceptors = new ClientInterceptor[] {new CustomClientInterceptor()};
    webServiceTemplate.setInterceptors(interceptors);

    return webServiceTemplate;
  }
}
