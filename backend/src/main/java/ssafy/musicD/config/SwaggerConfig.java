package ssafy.musicD.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("musicD").description("APIs").build();
	}

	@Bean
	public Docket commonApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("musicD").apiInfo(this.apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("ssafy.musicD.controller")).paths(PathSelectors.ant("/api/**"))
				.build();
	}
}
