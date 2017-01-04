package de.lv1871.dms.QuestionnaireWebKata.api.documentation;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class AntwortensatzResourceDoc {

	@Bean
	public Docket antwortensatzApi() {
		// @formatter:off
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("antwortensatzapi")
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.select()
				.paths(fragensatzPaths())
				.build();
		// @formatter:on
	}

	private Predicate<String> fragensatzPaths() {
		// @formatter:off
		return Predicates.and(
				regex("/api/antwortensatz.*"),
				Predicates.and(
						Predicates.not(regex("/api/antwortensatz/.*/antwort.*")),
						Predicates.not(regex("/api/antwortensatz/.*/ergebnis.*"))));
		// @formatter:on
	}

	private ApiInfo apiInfo() {
		// @formatter:off
		return new ApiInfoBuilder()
				.title("Antworten")
				.description("Diese Resource ermöglicht den Zugriff und die Erzeugung "
						+ "eines Antwortensatzes. Um Antworten zu geben muss zuvor hier "
						+ "ein Kontext erstellt werden, unter dem sich alle Antworten "
						+ "gruppieren. Zudem können hier Antworten gegeben werden. Um "
						+ "eine Antwort zu geben muss zuvor ein Antwortensatz erstellt werden.")
				.build();
		// @formatter:on
	}

}
