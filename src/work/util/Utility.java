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
   * 
   * @param max �������� ���� ���� ū��
   * @param num �������� ������ ���� ����
   * @return
   */
  public static int[] getRandomNums(int max, int num) {
    int[] arr = new int[num];
    
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * (max - 1)) + 1;
    }
    
    return arr;
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
  
  /**
   * '����/�浵' �������� �� ��ǥ�� �и��ؼ� ��ȯ
   * 
   * @param coords
   * @return
   */
  public static String[] getLatLng(String coords) {
    String[] latlng = null;
    
    if (coords != null) {
      latlng = coords.split("/");
    }
    
    return latlng;
  }
  
  /**
   * ��ǥ�� �������� �Ÿ��� ���� �� ������ �ɸ��� �ð��� ��ȯ�Ѵ�(�� ����)
   * @param coords1
   * @param coords2
   * @return
   */
  public static int getTimeWithCoords(String[] coords1, String[] coords2) {
    int timeMin = 0;
    double earthRadiusKilometer = 6371;
    double distanceKilometer = 0;
    
    double degLat1 = Double.valueOf(coords1[0]);
    double degLng1 = Double.valueOf(coords1[1]);
    
    // radian = degree * pi/180
    double radLat1 = degLat1 * (Math.PI / 180.0);
    double radLng1 = degLng1 * (Math.PI / 180.0);
    
    double degLat2 = Double.valueOf(coords2[0]);
    double degLng2 = Double.valueOf(coords2[1]);
    
    // radian = degree * pi/180
    double radLat2 = degLat2 * (Math.PI / 180.0);
    double radLng2 = degLng2 * (Math.PI / 180.0);
    
    // get distance(km) using latitude longitude
    double rad = Math.acos(Math.sin(radLat1) * Math.sin(radLat2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radLng1 - radLng2));
    
    distanceKilometer = earthRadiusKilometer * rad;
    
    // ������ �ü��� ��� 4km/h ������ �м��� 67m/min
    double distanceMeter = distanceKilometer * 1000;
    timeMin = (int) (distanceMeter / 67);
    
    return timeMin;
  }
}