package de.lv1871.dms.QuestionnaireWebKata.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.lv1871.dms.QuestionnaireWebKata.api.documentation.SwaggerApiDocConstant;
import de.lv1871.dms.QuestionnaireWebKata.domain.Ergebnis;
import de.lv1871.dms.QuestionnaireWebKata.domain.RestError;
import de.lv1871.dms.QuestionnaireWebKata.service.ErgebnisService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/antwortensatz/{antwortensatzId}/ergebnis")
@CrossOrigin
public class ErgebnisResource {

	@Autowired
	private ErgebnisService ergebnisService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(code = 404, response = RestError.class, message = SwaggerApiDocConstant.RESOURCE_NOT_FOUND_API_DESCRIPTION),
			@ApiResponse(code = 500, response = RestError.class, message = SwaggerApiDocConstant.INTERNAL_SERVER_ERROR_API_DESCRIPTION) })
	public Ergebnis getOne(@PathVariable long antwortensatzId) {
		return ergebnisService.getErgebnis(antwortensatzId);
	}

}
