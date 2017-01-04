package de.lv1871.dms.QuestionnaireWebKata.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.lv1871.dms.QuestionnaireWebKata.api.documentation.SwaggerApiDocConstant;
import de.lv1871.dms.QuestionnaireWebKata.domain.Antwortensatz;
import de.lv1871.dms.QuestionnaireWebKata.domain.RestError;
import de.lv1871.dms.QuestionnaireWebKata.service.AntwortService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/antwortensatz")
@CrossOrigin
public class AntwortensatzResource {

	@Autowired
	private AntwortService antwortService;

	@RequestMapping(path = "/", method = RequestMethod.POST)
	@ApiResponses(value = {
			@ApiResponse(code = 500, response = RestError.class, message = SwaggerApiDocConstant.INTERNAL_SERVER_ERROR_API_DESCRIPTION) })
	public Long newAntwortsatz() {
		return antwortService.createAntwortenSatz();
	}

	@RequestMapping(path = "/{antwortensatzId}", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(code = 404, response = RestError.class, message = SwaggerApiDocConstant.RESOURCE_NOT_FOUND_API_DESCRIPTION),
			@ApiResponse(code = 500, response = RestError.class, message = SwaggerApiDocConstant.INTERNAL_SERVER_ERROR_API_DESCRIPTION) })
	public Antwortensatz getOne(@PathVariable long antwortensatzId) {
		return antwortService.getAntwortensatz(antwortensatzId);
	}

}
