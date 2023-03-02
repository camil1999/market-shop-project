package az.marketshopjamil.MarketShopJamil.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import az.marketshopjamil.MarketShopJamil.exception.AlreadyDefinedException;
import az.marketshopjamil.MarketShopJamil.exception.MyAccessDeniedException;
import az.marketshopjamil.MarketShopJamil.exception.MyValidationException;
import az.marketshopjamil.MarketShopJamil.exception.NotDeleteSelfException;
import az.marketshopjamil.MarketShopJamil.exception.NotFoundException;

@RestControllerAdvice
public class GlobalAdvice {
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<String> handleMyValidationException(MyValidationException exc) {
		BindingResult br = exc.getBr();
		ArrayList<String> errors = new ArrayList<>();
		for (FieldError e : br.getFieldErrors()) {
			errors.add(e.getField() + "-" + e.getDefaultMessage());
		}
		return errors;

	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String handleNotFoundExceptionn(NotFoundException exc) {
		String error = exc.getMessage();

		return error;

	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public String handleNotDeleteSelfException(NotDeleteSelfException exception) {

		return exception.getMessage();

	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public String handleUsernameAlreadyDefinedException(AlreadyDefinedException exception) {

		return exception.getMessage();

	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public String handleMyAccessDeniedException(MyAccessDeniedException exception) {

		return exception.getMessage();

	}

}
