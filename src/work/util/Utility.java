package work.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utility {
	/**
	 * �����ڵ� (4�ڸ�) ����
	 * 
	 * @return
	 */
	public static String getSecurityCode() {
		return getSecurityCode();
	}

	/**
	 * �����ڵ� ����
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
	 * ���� ��¥ (YYYY.MM.DD) ���� ��ȯ
	 * 
	 * @return
	 */
	public static String getTodayDate() {
		return getTodayDate("YYYY.MM.DD");
	}

	/**
	 * ���� ��¥�� format�� ���߾� ��ȯ
	 * 
	 * @param format
	 * @return
	 */
	public static String getTodayDate(String format) {
		SimpleDateFormat sd = new SimpleDateFormat(format);

		return sd.format(new Date());
	}

	/**
	 * ���� 3�ڸ����� , �� ��� ��ȯ
	 * 
	 * @param value
	 * @return
	 */
	public static String getCommaString(String value) {
		return NumberFormat.getInstance().format(value);
	}

	/**
	 * ���ڸ� ��ȭ �������� ��ȯ
	 * 
	 * @param value
	 * @return
	 */
	public static String getCurrencyString(String value) {
		return getCurrencyString(value, Locale.KOREA);
	}

	/**
	 * �����Ͽ� �´� ��ȭ �������� ��ȯ
	 * 
	 * @param value
	 * @param locale
	 * @return
	 */
	public static String getCurrencyString(String value, Locale locale) {
		return NumberFormat.getCurrencyInstance(locale).format(value);
	}

	/**
	 * Ư�� ���ڿ��� �Ϻκ��� *�� replace�Ͽ� ��ȯ
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