package de.scout24.financing;

import java.util.Properties;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

@SpringBootApplication
@EnableJpaRepositories("de.scout24.financing.repository")
@ComponentScan(basePackages = "de.scout24.financing")
@EnableScheduling
public class ExpertForumApplication extends SpringBootServletInitializer {

    private static final int CONNECTOR_PORT = 8444;
    private static final int PROXY_PORT = 443;

//    @Value("${exfo.tomcat.proxy.name}")
//    private String proxyName;
//    @Value("${server.ssl.key-store}")
//    private String keystoreFile;
//    @Value("${server.ssl.key-store-password}")
//    private String keystorePass;
    
    private static final String EXTERNAL_CONFIG_PATH = "/opt/exfo/";

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ExpertForumApplication.class, EmbeddedSolrContext.class, SolrContext.class, RestMvcConfiguration.class, 
                WebSecurityConfig.class)
                .properties(getProperties());
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put("spring.config.location", EXTERNAL_CONFIG_PATH);
        return props;
    }

    public static void main(String[] args) {
        SpringApplication.run(ExpertForumApplication.class, args);
    }

    @Bean
    @Profile("local")
    public EmbeddedServletContainerFactory localServletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = initTomcat();
        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

//    @Bean
//    @Profile({ "dev", "stage", "prod" })
//    public EmbeddedServletContainerFactory nonLocalservletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = initTomcat();
//        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//        tomcat.addAdditionalTomcatConnectors(createProxyConnector());
//        return tomcat;
//    }
//
    private TomcatEmbeddedServletContainerFactory initTomcat() {
        return new TomcatEmbeddedServletContainerFactory() {

            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
    }

    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }

//    private Connector createProxyConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setPort(CONNECTOR_PORT);
//        //connector.setProxyName(proxyName);
//        connector.setProxyPort(PROXY_PORT);
//        connector.setScheme("https");
//        connector.setAttribute("sslProtocol", "TLS");
//        connector.setAttribute("clientAuth", false);
//        //connector.setAttribute("keystoreFile", keystoreFile);
//        //connector.setAttribute("keystorePass", keystorePass);
//        connector.setAttribute("SSLEnabled", true);
//        return connector;
//    }

    @Bean
    public MDCInsertingServletFilter mdcInsertingServletFilter() {
        return new MDCInsertingServletFilter();
    }

}