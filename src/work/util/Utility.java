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
   * 
   * @param max 랜덤으로 받을 가장 큰값
   * @param num 랜덤으로 가져올 숫자 갯수
   * @return
   */
  public static int[] getRandomNums(int max, int num) {
    int[] arr = new int[num];
    
    
    /*
     * if (arr[0] == arr[1] || arr[0] == arr[2] || arr[1] == arr[2]) { for (int i=0; i<3; i++) {
     * 
     * }
     */
    for (int i = 0; i < 3; i++) {
      arr[i] = (int) (Math.random() * (max - 1)) + 1;
      for (int j = 0; j < i; j++) {
        if (arr[i] == arr[j]) {
          i--;
          break;
        }
      }
    }
    
    return arr;
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
  
  /**
   * '위도/경도' 형식으로 된 좌표를 분리해서 반환
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
   * 좌표를 바탕으로 거리를 구한 후 도보로 걸리는 시간을 반환한다(분 단위)
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
    
    // 도보의 시속은 평균 4km/h 도보의 분속은 67m/min
    double distanceMeter = distanceKilometer * 1000;
    timeMin = (int) (distanceMeter / 67);
    
    return timeMin;
  }
}