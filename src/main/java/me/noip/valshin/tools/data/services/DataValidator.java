package me.noip.valshin.tools.data.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import me.noip.valshin.tools.data.Validator;
import me.noip.valshin.tools.data.constants.ValidatorConstants;

@Service
public class DataValidator implements Validator{
	protected Logger logger = Logger.getLogger(DataValidator.class.getName());
	private boolean checkMaxLength(String value, int max){
		return value.length() <= max;
	}
	
	private boolean checkMinLength(String value, int min){
		return value.length() >= min;
	}
	
	private boolean checkLength(String value, int min){
		return checkMinLength(value, min) && checkMaxLength(value, ValidatorConstants.MAX_VALUE_LENGTH);
	}
	
	private boolean checkByPattern(String value, String patternString){
		try {
			Pattern pattern = Pattern.compile(patternString);
			Matcher matcher = pattern.matcher(value);
			return matcher.matches();
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean checkLogin(String login) {
		return checkByPattern(login, ValidatorConstants.LOGIN_PATTERN);
	}

	@Override
	public boolean checkPassword(String password) {
		return checkByPattern(password, ValidatorConstants.LOGIN_PATTERN);
	}

	@Override
	public boolean checkFio(String fio) {
		return checkLength(fio, ValidatorConstants.MIN_FIO_LENGTH);
	}

	@Override
	public boolean checkName(String name) {
		return checkLength(name, ValidatorConstants.MIN_NAME_LENGTH);
	}

	@Override
	public boolean checkSecondName(String secondName) {
		return checkLength(secondName, ValidatorConstants.MIN_SECOND_NAME_LENGTH);
	}

	@Override
	public boolean checkLastName(String lastName) {
		return checkLength(lastName, ValidatorConstants.MIN_LAST_NAME_LENGTH);
	}

	@Override
	public boolean checkPhone(String phone) {
		return checkByPattern(phone, ValidatorConstants.PHONE_PATTERN);
	}

	@Override
	public boolean checkHomePhone(String homePhone) {
		if (homePhone.equals("")) return true;
		return checkByPattern(homePhone, ValidatorConstants.PHONE_PATTERN);
	}

	@Override
	public boolean checkAdress(String address) {
		if (address.equals("")) return true;
		return checkMaxLength(address, ValidatorConstants.MAX_VALUE_LENGTH);
	}

	@Override
	public boolean checkEmail(String email) {
		if (email.equals("")) return true;
		return checkByPattern(email, ValidatorConstants.EMAIL_PATTERN);
	}

}
