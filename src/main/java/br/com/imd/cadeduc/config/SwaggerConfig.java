package br.com.imd.cadeduc.config;



import java.time.LocalDate;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = SwaggerConfig.class)
public class SwaggerConfig extends WebMvcConfigurationSupport{                                    
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()
	          .apis(RequestHandlerSelectors.basePackage("br.com.imd.cadeduc"))
	          .paths(PathSelectors.regex("/.*"))
	          .build()
          .pathMapping("/")
          .directModelSubstitute(LocalDate.class, String.class)
          .genericModelSubstitutes(ResponseEntity.class)
          .useDefaultResponseMessages(false)
          .globalResponseMessage(RequestMethod.GET,
        		  ResponseMessagesConfig.getInstance().listaMensagens())
          .globalResponseMessage(RequestMethod.POST,
        		  ResponseMessagesConfig.getInstance().listaMensagens())
          .apiInfo(metaData());

    }
    	
	private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "CADEduc",
                "Spring Boot REST API para Geocalização de escolas",
                "1.0",
                "Termos e Serviço",
                new Contact("CADEduc", "https://cadeduc.imd.com.br/about/", "miguelwelligton@gmail.com"),
               "GNU General Public License 3.0",
                "https://www.gnu.org/licenses/gpl.txt", Collections.emptyList());
        return apiInfo;
    }
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}