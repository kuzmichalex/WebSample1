package com.htp.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan("com.htp")
@Import({DatasourceConfiguration.class})
public class ApplicationMainConfig {
}
