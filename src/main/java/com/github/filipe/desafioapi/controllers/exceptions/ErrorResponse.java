package com.github.filipe.desafioapi.controllers.exceptions;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
	private int status;
	private String message;
	private List<String> details;

	public ErrorResponse(int status, String message) {
		this(status, message, null);
	}
}
