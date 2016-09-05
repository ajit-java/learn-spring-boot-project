package de.scout24.financing;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

@Configuration
@EnableSolrRepositories("de.scout24.financing.solr.repository")
@Profile("local")
public class EmbeddedSolrContext {

    @Value("${solr.embedded.url}")
    private String embeddedSolrUrl;

    @Bean
    public SolrServer embeddedSolrServer() throws ParserConfigurationException, IOException, SAXException {
        EmbeddedSolrServerFactory factory = new EmbeddedSolrServerFactory(embeddedSolrUrl);
        return factory.getSolrServer();
    }

    @Bean
    public SolrTemplate solrTemplate() throws Exception {
        return new SolrTemplate(embeddedSolrServer());
    }
}
