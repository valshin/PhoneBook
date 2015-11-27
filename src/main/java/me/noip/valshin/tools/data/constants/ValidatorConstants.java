package me.noip.valshin.tools.data.constants;

public class ValidatorConstants {
	public static final int MAX_VALUE_LENGTH = 200;
//	Notes
	public static final int MIN_NAME_LENGTH = 4;
	public static final int MIN_SECOND_NAME_LENGTH = 4;
	public static final int MIN_LAST_NAME_LENGTH = 4;
	public static final String PHONE_PATTERN = "\\d{10}";
	public static final String EMAIL_PATTERN = 
			"^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
//	Users
	public static final String LOGIN_PATTERN = "^(?!.*[_\\W]).{3,}$";
	public static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d).{5,}$";
	public static final int MIN_FIO_LENGTH = 5;
	public static final int MIN_PASSWORD_UPPERCASE_LETTER_COUNT = 1;
	public static final int MIN_PASSWORD_NUMBERS_COUNT = 1;
}
