package net.chandol.study.board.config;


import no.api.freemarker.java8.Java8ObjectWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

@Configuration
public class FreemarkerConfiguration {

    @Autowired
    void configureFreeMarkerConfigurer(FreeMarkerConfig configurer) {
        Java8ObjectWrapper java8ObjectWrapper = new Java8ObjectWrapper(freemarker.template.Configuration.getVersion());
        configurer.getConfiguration().setObjectWrapper(java8ObjectWrapper);
    }
}