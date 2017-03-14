package work.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utility {
	/**
	 * 보안코드 (4자리) 생성
	 * 
	 * @return
	 */
	public static String getSecurityCode() {
		return getSecurityCode();
	}

	/**
	 * 보안코드 생성
	 * 
	 * @param length
	 * @return
	 */
	public static String getSecurityCode(int length) {
		String securityCode = "";

		for (int i = 0; i < length; i++) {
			securityCode += (int) (Math.random() * 10);
		}

		return securityCode;
	}

	/**
	 * 오늘 날짜 (YYYY.MM.DD) 형식 반환
	 * 
	 * @return
	 */
	public static String getTodayDate() {
		return getTodayDate("YYYY.MM.DD");
	}

	/**
	 * 오늘 날짜를 format에 맞추어 반환
	 * 
	 * @param format
	 * @return
	 */
	public static String getTodayDate(String format) {
		SimpleDateFormat sd = new SimpleDateFormat(format);

		return sd.format(new Date());
	}

	/**
	 * 숫자 3자리마다 , 를 찍어 반환
	 * 
	 * @param value
	 * @return
	 */
	public static String getCommaString(String value) {
		return NumberFormat.getInstance().format(value);
	}

	/**
	 * 숫자를 통화 형식으로 반환
	 * 
	 * @param value
	 * @return
	 */
	public static String getCurrencyString(String value) {
		return getCurrencyString(value, Locale.KOREA);
	}

	/**
	 * 로케일에 맞는 통화 형식으로 반환
	 * 
	 * @param value
	 * @param locale
	 * @return
	 */
	public static String getCurrencyString(String value, Locale locale) {
		return NumberFormat.getCurrencyInstance(locale).format(value);
	}

	/**
	 * 특정 문자열의 일부분을 *로 replace하여 반환
	 * 
	 * @param userPw
	 * @param startIndex
	 * @return
	 */
	public static String getPwString(String userPw, int startIndex) {
		String result = "";
		
		for (int i = 0; i < userPw.length(); i++) {
			if (i >= startIndex) {
				result += "*";
			} else {
				result += userPw.charAt(i);
			}
		}
		
		return result;
	}
}