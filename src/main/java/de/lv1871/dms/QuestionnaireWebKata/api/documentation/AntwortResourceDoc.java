package de.lv1871.dms.QuestionnaireWebKata.api.documentation;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class AntwortResourceDoc {

	@Bean
	public Docket antwortApi() {
		// @formatter:off
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("antwortapi")
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.select()
				.paths(fragensatzPaths())
				.build();
		// @formatter:on
	}

	private Predicate<String> fragensatzPaths() {
		return regex("/api/antwortensatz/.*/antwort.*");
	}

	private ApiInfo apiInfo() {
		// @formatter:off
		return new ApiInfoBuilder()
				.title("Antworten")
				.description("Über diese Resource werden Antworten gegeben "
						+ "und einzelne Antworten auch wieder abgerufen.")
				.build();
		// @formatter:on
	}

}
