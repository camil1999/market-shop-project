package az.marketshopjamil.MarketShopJamil.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import az.marketshopjamil.MarketShopJamil.exception.AlreadyDefinedException;
import az.marketshopjamil.MarketShopJamil.exception.MyAccessDeniedException;
import az.marketshopjamil.MarketShopJamil.exception.MyValidationException;
import az.marketshopjamil.MarketShopJamil.exception.NotDeleteSelfException;
import az.marketshopjamil.MarketShopJamil.exception.NotFoundException;
import az.marketshopjamil.MarketShopJamil.response.ErrorResponse;

@RestControllerAdvice
public class GlobalAdvice {
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<ErrorResponse> handleMyValidationException(MyValidationException exc) {
		BindingResult br = exc.getBr();
		List<ErrorResponse> msg = new ArrayList<ErrorResponse>();
		for (FieldError e : br.getFieldErrors()) {
			ErrorResponse errorMessage = new ErrorResponse();
			errorMessage.setMessage(e.getField() + "-" + e.getDefaultMessage());
			errorMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
			msg.add(errorMessage);
		}
		return msg;
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorResponse handleNotFoundExceptionn(NotFoundException exc, WebRequest request) {
		ErrorResponse errorMessage = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage());

		return errorMessage;

	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ErrorResponse handleNotDeleteSelfException(NotDeleteSelfException exception) {
		ErrorResponse errorMessage = new ErrorResponse(HttpStatus.CONFLICT.value(), exception.getMessage());
		return errorMessage;

	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ErrorResponse handleUsernameAlreadyDefinedException(AlreadyDefinedException exception, WebRequest request) {
		ErrorResponse errorMessage = new ErrorResponse(HttpStatus.CONFLICT.value(), exception.getMessage());
		return errorMessage;

	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public ErrorResponse handleMyAccessDeniedException(MyAccessDeniedException exception) {
		ErrorResponse errorMessage = new ErrorResponse(HttpStatus.FORBIDDEN.value(), exception.getMessage());
		return errorMessage;

	}

}
