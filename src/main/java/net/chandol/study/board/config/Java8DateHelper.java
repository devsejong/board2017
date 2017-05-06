package net.chandol.study.board.config;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;

@Configuration
public class Java8DateHelper {

    @Autowired HandlebarsViewResolver handlebarsViewResolver;

    @PostConstruct
    public void registerHelper() {
        handlebarsViewResolver.registerHelper("temporalFormat", new Java8TemporalFormatHelper());
    }

    static class Java8TemporalFormatHelper<T> implements Helper<T> {
        @Override
        public Object apply(Object object, Options options) throws IOException {
            Class<?> sourceClass = object.getClass();
            String pattern = options.param(0, "yy/MM/dd hh:mm:ss");

            if (OffsetDateTime.class == sourceClass) {
                OffsetDateTime dateTime = OffsetDateTime.class.cast(object);
                return dateTime.format(ofPattern(pattern));
            }
            else if (LocalDateTime.class == sourceClass) {
                LocalDateTime dateTime = LocalDateTime.class.cast(object);
                return dateTime.format(ofPattern(pattern));
            }
            else {
                return object;
            }
        }
    }
}
