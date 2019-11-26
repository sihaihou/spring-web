package com.reyco.kn.core;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//加了@ServletComponentScan,无论过滤器类加不加@Componment都可以,单使用@Component会默认过滤/*,
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@MapperScan("com.reyco.kn.core.dao")
@SpringBootApplication
public class KNApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KNApplication.class, args);
	}
	
	/**
	 * 解决cookie域名格式:
	 * 		An invalid domain [] was specified for this cookie问题解决方案
	 * @return
	 */
	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
		return (factory) -> factory.addContextCustomizers(
				(context) -> context.setCookieProcessor(new LegacyCookieProcessor()));
	}
	
}
