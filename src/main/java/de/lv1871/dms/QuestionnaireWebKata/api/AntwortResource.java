package de.lv1871.dms.QuestionnaireWebKata.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.lv1871.dms.QuestionnaireWebKata.api.documentation.SwaggerApiDocConstant;
import de.lv1871.dms.QuestionnaireWebKata.domain.Antwort;
import de.lv1871.dms.QuestionnaireWebKata.domain.RestError;
import de.lv1871.dms.QuestionnaireWebKata.service.AntwortService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/antwortensatz/{antwortensatzId}/antwort")
@CrossOrigin
public class AntwortResource {

	@Autowired
	private AntwortService antwortService;

	@RequestMapping(path = "/", method = RequestMethod.POST)
	@ApiResponses(value = {
			@ApiResponse(code = 500, response = RestError.class, message = SwaggerApiDocConstant.INTERNAL_SERVER_ERROR_API_DESCRIPTION),
			@ApiResponse(code = 400, response = RestError.class, message = SwaggerApiDocConstant.BAD_REQUEST_API_DESCRIPTION) })
	public Long newAntwort(@PathVariable Long antwortensatzId, @RequestBody Antwort antwort) {
		return antwortService.addAntwort(antwortensatzId, antwort);
	}

	@RequestMapping(path = "/{antwortId}", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(code = 404, response = RestError.class, message = SwaggerApiDocConstant.RESOURCE_NOT_FOUND_API_DESCRIPTION),
			@ApiResponse(code = 500, response = RestError.class, message = SwaggerApiDocConstant.INTERNAL_SERVER_ERROR_API_DESCRIPTION) })
	public Antwort getOne(@PathVariable long antwortensatzId, @PathVariable long antwortId) {
		return antwortService.getAntwort(antwortensatzId, antwortId);
	}

}
