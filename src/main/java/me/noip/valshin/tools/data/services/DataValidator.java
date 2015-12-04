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
		return value.length() > min;
	}
	
	private boolean checkLength(String value, int min){
		return checkMinLength(value, min) && checkMaxLength(value, ValidatorConstants.MAX_VALUE_LENGTH);
	}
	
	private boolean checkByPattern(String value, String patternString){
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
	@Override
	public boolean checkLogin(String login) {
		if (login == null) return false;
		return checkByPattern(login, ValidatorConstants.LOGIN_PATTERN);
	}

	@Override
	public boolean checkPassword(String password) {
		if (password == null) return false;
		return checkByPattern(password, ValidatorConstants.LOGIN_PATTERN);
	}

	@Override
	public boolean checkFio(String fio) {
		if (fio == null) return false;
		return checkLength(fio, ValidatorConstants.MIN_FIO_LENGTH);
	}

	@Override
	public boolean checkName(String phone) {
		if (phone == null) return false;
		return checkLength(phone, ValidatorConstants.MIN_NAME_LENGTH);
	}

	@Override
	public boolean checkSecondName(String secondName) {
		if (secondName == null) return false;
		return checkLength(secondName, ValidatorConstants.MIN_SECOND_NAME_LENGTH);
	}

	@Override
	public boolean checkLastName(String lastName) {
		if (lastName == null) return false;
		return checkLength(lastName, ValidatorConstants.MIN_LAST_NAME_LENGTH);
	}

	@Override
	public boolean checkPhone(String phone) {
		if (phone == null) return false;
		return checkByPattern(phone, ValidatorConstants.PHONE_PATTERN);
	}

	@Override
	public boolean checkHomePhone(String homePhone) {
		if (homePhone == null) return true;
		return checkByPattern(homePhone, ValidatorConstants.PHONE_PATTERN);
	}

	@Override
	public boolean checkAdress(String adress) {
		if (adress == null) return true;
		return checkMaxLength(adress, ValidatorConstants.MAX_VALUE_LENGTH);
	}

	@Override
	public boolean checkEmail(String email) {
		if (email == null) return true;
		return checkByPattern(email, ValidatorConstants.EMAIL_PATTERN);
	}

}
