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
public class FragensatzResourceDoc {

	@Bean
	public Docket fragensatzApi() {
		// @formatter:off
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("fragensatzapi")
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.select()
				.paths(fragensatzPaths())
				.build();
		// @formatter:on
	}

	private Predicate<String> fragensatzPaths() {
		return regex("/api/fragensatz.*");
	}

	private ApiInfo apiInfo() {
		// @formatter:off
		return new ApiInfoBuilder()
				.title("Fragensätze")
				.description("Diese Resource bietet Zugriff auf alle oder eine Frage die "
						+ "auch die Möglichen Antworten enthält.")
				.build();
		// @formatter:on
	}

}
