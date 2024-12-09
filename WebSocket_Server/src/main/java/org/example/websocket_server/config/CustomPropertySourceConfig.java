package org.example.websocket_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
// docker
//@PropertySource("file:/etc/env/local.env")
//local
@PropertySource("file:/Users/samvel/My_projects/JavaProjects/onlineChat/config_files/local.env")
public class CustomPropertySourceConfig {
}
