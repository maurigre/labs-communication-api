package br.com.magalu.labs.communication.config.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private final static String EMAIL = "maurigre@gmail.com";
    private final static String SITE = "http://maurireis.com.br";
    private final static String TITLE = "Mauri Reis";
    private final static String TITLE_LICENSE = "GC License";
    private final static String URL_TERMS_OF_SERVICE = "Terms of Service";
    private final static String LICENCE_URL = SITE;

   private BuildProperties build;


    @Autowired
    public SwaggerConfiguration(BuildProperties build) {
        this.build = build;
    }

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                build.getName(),
                build.getName() + " - " + build.get("description"),
                build.getVersion(),
                URL_TERMS_OF_SERVICE,
                new Contact(
                        TITLE,
                        SITE,
                        EMAIL
                ),
                TITLE_LICENSE,
                LICENCE_URL,
                Collections.emptyList()
        );
    }
}
