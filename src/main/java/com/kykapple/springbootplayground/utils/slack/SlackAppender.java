package com.kykapple.springbootplayground.utils.slack;

import com.kykapple.springbootplayground.utils.PropertyLoader;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.Serializable;
import java.util.Properties;

@Log4j2
@Plugin(
        name = "SlackAppender",
        category = Core.CATEGORY_NAME,       // appender, layout, filter 등은 core plugin에 속한다.
        elementType = Appender.ELEMENT_TYPE
)
public class SlackAppender extends AbstractAppender {

    private String url;
    private String username;
    private WebClient webClient;

    public SlackAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
        super(name, filter, layout, true, Property.EMPTY_ARRAY);

        Properties properties = PropertyLoader.loadProperties();
        this.url = properties.getProperty("url");
        this.username = properties.getProperty("username");
        webClient = WebClient.create();
    }

    @PluginFactory
    public static SlackAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Filter") Filter filter,
            @PluginElement("Layout") Layout<? extends Serializable> layout
    ) {
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }

        return new SlackAppender(name, filter, layout);
    }

    @Override
    public void append(LogEvent event) {
        if (event.getLevel().isLessSpecificThan(Level.WARN)) {
            return;
        }

        logToSlack(event);
    }

    private void logToSlack(LogEvent event) {
        webClient.post()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(createLogFormat(event))
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }

    private SlackLogFormat createLogFormat(LogEvent event) {
        byte[] bytes = getLayout().toByteArray(event);

        return new SlackLogFormat(username, new String(bytes));
    }



}
