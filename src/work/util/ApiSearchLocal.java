/**
 * 
 */
package work.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 네이버 검색 api 관련 클래스
 * @author DB
 *
 */
public class ApiSearchLocal {
	public static String search(String param) {
        String clientId = "xR22H5I3UVJVZ_m677sf";	//애플리케이션 클라이언트 아이디값";
        String clientSecret = "cCwJz0BFhR";				//애플리케이션 클라이언트 시크릿값";
        String result = null;
        try {
            String text = URLEncoder.encode(param, "UTF-8");
            //String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text; // json 결과
            String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
            
            URL url = new URL(apiURL);
            
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            
            int responseCode = con.getResponseCode();
            BufferedReader br;
            
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            
            System.out.println(response.toString());
            
            result = response.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
