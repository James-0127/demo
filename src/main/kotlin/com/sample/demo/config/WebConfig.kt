package com.sample.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.filter.HiddenHttpMethodFilter
import java.util.*
import javax.servlet.DispatcherType
import javax.servlet.ServletContext

//@Configuration
class WebConfig: WebApplicationInitializer {

    override fun onStartup(servletContext: ServletContext) {
        servletContext.addFilter("httpMethodFilter", HiddenHttpMethodFilter())
                      .addMappingForUrlPatterns( EnumSet.allOf(DispatcherType::class.java), false, "/*")
    }
}