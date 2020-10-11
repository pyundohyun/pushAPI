package com.example.testingweb;

import java.io.FileInputStream;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

@Service
public class GreetingService {
	public String greet() {
		return "Hello, World";
	}

	public String push(String pushkey) {
		 try {    
	            
			 	// 절대경로로 json 경로 박아야
	            String path = "/Users/pyeondohyeon/Documents/workspace-spring-tool-suite-4-4.8.0.RELEASE/gs-testing-web-complete/src/resources/pushJson.json";           
	            String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
	            String[] SCOPES = { MESSAGING_SCOPE };
	            
	            GoogleCredential googleCredential = GoogleCredential
	                                .fromStream(new FileInputStream(path))
	                                .createScoped(Arrays.asList(SCOPES));
	            googleCredential.refreshToken();
	                                
	            HttpHeaders headers = new HttpHeaders();
	            headers.add("content-type" , MediaType.APPLICATION_JSON_VALUE);
	            headers.add("Authorization", "Bearer " + googleCredential.getAccessToken());
	            	            
	            JSONObject notification = new JSONObject();
	            notification.put("body", "푸시내용");
	            notification.put("title","푸시타이틀");
	            
	            JSONObject message = new JSONObject();
	
	            // 상대방 푸시키 
	            // android 
	            //message.put("token", "eQ2_hrk-ToWhfrcU0b3S6R:APA91bGW9jO754I8J2VDDtdTLFzNH39ABH9o_wvwJOGRPRYI4hQsosUuR6UdFxt3Y_iSpRpX4ILWpgd9j3RXUtg1BB5e8_zR4F1r53eIvvcY8lGgHcvUhKdvk8Pd8E6SXeWbNwWAgl0j");
	            
	            // ios 	            
	            message.put("token", pushkey);
	            message.put("notification", notification);
	            
	            JSONObject jsonParams = new JSONObject();
	            jsonParams.put("message", message);
	            
	            HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonParams, headers);
	            RestTemplate rt = new RestTemplate();            
	            
	            // 프로젝트id -json안에 있 --프로젝트 아이디 필요!! url 중간
	            ResponseEntity<String> res = rt.exchange("https://fcm.googleapis.com/v1/projects/pushtest-6ee09/messages:send"
	                    , HttpMethod.POST
	                    , httpEntity
	                    , String.class);
	        
	            if (res.getStatusCode() != HttpStatus.OK) {
	                System.out.println("FCM-Exception");
	                System.out.println(res.getStatusCode().toString());
	                System.out.println(res.getHeaders().toString());
	                System.out.println(res.getBody().toString());
	                
	            } else {
	                System.out.println(res.getStatusCode().toString());
	                System.out.println(res.getHeaders().toString());
	                System.out.println(res.getBody().toLowerCase());
	                
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return "Hello, push";
	}

	
}
