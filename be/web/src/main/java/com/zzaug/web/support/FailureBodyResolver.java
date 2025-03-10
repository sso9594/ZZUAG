package com.zzaug.web.support;

import com.zzaug.web.support.ApiResponse.FailureBody;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import lombok.experimental.UtilityClass;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@UtilityClass
public class FailureBodyResolver {

	public static FailureBody resolveFrom(final ConstraintViolationException ex) {
		return ex.getConstraintViolations().stream()
				.map(
						v -> {
							String fieldName = "";
							for (Path.Node node : v.getPropertyPath()) {
								fieldName = node.getName();
							}

							return new FailureBody(fieldName, v.getMessage());
						})
				.findFirst()
				.orElse(null);
	}

	public static FailureBody resolveFrom(final ServletRequestBindingException ex) {
		return new FailureBody(ex.getMessage());
	}

	public static FailureBody resolveFrom(final TypeMismatchException ex) {
		return new FailureBody(ex.getMessage());
	}

	public static FailureBody resolveFrom(final BindException ex) {
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			return new FailureBody(error.getCode(), error.getDefaultMessage());
		}
		return null;
	}

	public static FailureBody resolveFrom(final HttpRequestMethodNotSupportedException ex) {
		return new FailureBody(ex.getLocalizedMessage());
	}

	public static FailureBody resolveFrom(final HttpMediaTypeNotSupportedException ex) {
		return new FailureBody(ex.getLocalizedMessage());
	}

	public static FailureBody resolveFrom(final HttpMediaTypeNotAcceptableException ex) {
		return new FailureBody(ex.getLocalizedMessage());
	}

	public static FailureBody resolveFrom(HttpMessageNotReadableException ex) {
		return new FailureBody(ex.getLocalizedMessage());
	}

	public static FailureBody resolveFrom(MissingServletRequestPartException ex) {
		return new FailureBody(ex.getLocalizedMessage());
	}
}
