package de.lv1871.dms.QuestionnaireWebKata.domain;

public class RestError {

	private final int statusCode;
	private final String message;

	public RestError(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}

}
