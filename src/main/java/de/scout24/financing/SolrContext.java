package de.scout24.financing;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories("de.scout24.financing.solr.repository")
@Profile({"dev", "stage", "prod"})
public class SolrContext {

    @Value("${solr.standalone.url}")
    private String solrUrl;
    
    @Bean
    public SolrServer solrServer() {
        return new HttpSolrServer(solrUrl);
    }
   
    @Bean
    public SolrTemplate solrTemplate(SolrServer server) throws Exception {
        return new SolrTemplate(server);
    }

}
