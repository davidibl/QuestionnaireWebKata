package de.lv1871.dms.QuestionnaireWebKata.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.lv1871.dms.QuestionnaireWebKata.api.documentation.SwaggerApiDocConstant;
import de.lv1871.dms.QuestionnaireWebKata.domain.Frage;
import de.lv1871.dms.QuestionnaireWebKata.domain.RestError;
import de.lv1871.dms.QuestionnaireWebKata.service.FrageService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/frage")
@CrossOrigin
public class FrageResource {

	@Autowired
	private FrageService fragenService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(code = 500, response = RestError.class, message = SwaggerApiDocConstant.INTERNAL_SERVER_ERROR_API_DESCRIPTION) })
	public List<Frage> getAll() {
		return fragenService.getAllFrage();
	}

	@RequestMapping(path = "/{frageId}", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(code = 404, response = RestError.class, message = SwaggerApiDocConstant.RESOURCE_NOT_FOUND_API_DESCRIPTION),
			@ApiResponse(code = 500, response = RestError.class, message = SwaggerApiDocConstant.INTERNAL_SERVER_ERROR_API_DESCRIPTION) })
	public Frage getOne(@PathVariable long frageId) {
		return fragenService.getFrage(frageId);
	}

}
