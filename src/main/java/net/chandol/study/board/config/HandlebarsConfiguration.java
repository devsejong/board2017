package net.chandol.study.board.config;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;

@Configuration
public class HandlebarsConfiguration extends WebMvcConfigurerAdapter{

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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CsrfAddingInterceptor());
        super.addInterceptors(registry);
    }

    public class CsrfAddingInterceptor extends HandlerInterceptorAdapter {
        public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView mav) throws Exception {
            CsrfToken token = (CsrfToken) req.getAttribute(CsrfToken.class.getName());
            if (token != null) {
                mav.addObject("_csrf", token);
            }
        }
    }
}
