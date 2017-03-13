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
 * ���̹� �˻� api ���� Ŭ����
 * @author DB
 *
 */
public class ApiSearchLocal {
	public static String search(String param) {
        String clientId = "xR22H5I3UVJVZ_m677sf";	//���ø����̼� Ŭ���̾�Ʈ ���̵�";
        String clientSecret = "cCwJz0BFhR";				//���ø����̼� Ŭ���̾�Ʈ ��ũ����";
        
        try {
            String text = URLEncoder.encode(param, "UTF-8");
            //String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text; // json ���
            String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml ���
            
            URL url = new URL(apiURL);
            
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            
            int responseCode = con.getResponseCode();
            BufferedReader br;
            
            if(responseCode==200) { // ���� ȣ��
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // ���� �߻�
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            
            System.out.println(response.toString());
            
            return response;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
