package com.spring.mochive.movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/mochive/movie")
public class MovieController {
	
	private static Map<String, String>  getRequestHeaders() {
		
		String Authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NWMxNDIyZTYwYzcyN2M3ODE1YWRlZmNmM2EyZmYzMCIsInN1YiI6IjY0ODk4MmEyYmYzMWYyNTA1ZjQwYTY2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KXOV_KodqQvbemETUXXvJA8zrm0OB0KSNvihzTBsFpo";
		
		Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", Authorization);
		
        return requestHeaders;
        
	}
	
	@GetMapping("/main")
	public ModelAndView search() throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		Map<String, String> requestHeaders = getRequestHeaders();
		
		List<MovieVO> nowPlaying = nowPlaying(requestHeaders);
		List<MovieVO> popular = popular(requestHeaders);
		List<MovieVO> topRated = topRated(requestHeaders);
		
		mav.setViewName("movie/MovieMain");
		mav.addObject("nowPlaying", nowPlaying);
		mav.addObject("popular", popular);
		mav.addObject("topRated", topRated);
		
		return mav;
				
	}

	@PostMapping("/find/result")
	public ModelAndView testPage(HttpServletRequest request) throws ParseException {
		
		System.out.println("결과");
		
		//int pages = getPages(type);
		
		ModelAndView mav = new ModelAndView();
		
		String Authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NWMxNDIyZTYwYzcyN2M3ODE1YWRlZmNmM2EyZmYzMCIsInN1YiI6IjY0ODk4MmEyYmYzMWYyNTA1ZjQwYTY2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KXOV_KodqQvbemETUXXvJA8zrm0OB0KSNvihzTBsFpo";

		String text = request.getParameter("searchKey");
        
        try {
            text = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
		
        
		String apiURL = "https://api.themoviedb.org/3/search/movie?query=";
		apiURL += text;
		apiURL += "&include_adult=true&language=ko&page=1";
		
		Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", Authorization);
        String responseBody = get(apiURL,requestHeaders);

        System.out.println(responseBody);

    	JSONParser jsonParser = new JSONParser();
    	JSONObject jsonObject = (JSONObject)jsonParser.parse(responseBody);
    	JSONArray list = (JSONArray)jsonObject.get("results");
    	
    	System.out.println(list);
    	
    	List<MovieVO> results = new ArrayList<MovieVO>();
    	
    	for (int i=0; i<list.size(); i++) {
    		
    		MovieVO vo = new MovieVO();
            
            jsonObject = (JSONObject)list.get(i);
            
            vo.setBackdropPath((String)jsonObject.get("backdrop_path"));
            vo.setId((Long)jsonObject.get("id"));
            vo.setOverview((String)jsonObject.get("overview"));
            String posterPath = "https://image.tmdb.org/t/p/w92";
            vo.setPosterPath(posterPath+(String)jsonObject.get("poster_path"));
            vo.setTitle((String)jsonObject.get("title"));
            
            results.add(vo);
            
    	}
    	
		mav.setViewName("movie/MovieSearch");
		mav.addObject("results", results);

    	return mav;
    	
	}
	
	private List<MovieVO> nowPlaying(Map<String, String> requestHeaders) {
		
		String apiURL = "https://api.themoviedb.org/3/movie/now_playing?language=ko&page=1";
		
		List<MovieVO> results = null;
		
		try {
			results = getData(apiURL, requestHeaders);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;		
		
	}
	
	
	private List<MovieVO> popular(Map<String, String> requestHeaders) {
		
		String apiURL = "https://api.themoviedb.org/3/movie/popular?language=ko&page=1";
		
		List<MovieVO> results = null;
		
		try {
			results = getData(apiURL, requestHeaders);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;		
		
	}
	
	private List<MovieVO> topRated(Map<String, String> requestHeaders) {
		
		String apiURL = "https://api.themoviedb.org/3/movie/top_rated?language=ko&page=1";
		
		List<MovieVO> results = null;
		
		try {
			results = getData(apiURL, requestHeaders);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;		
		
	}
	
	
	private List<MovieVO> getData(String apiUrl, Map<String, String> requestHeaders) throws ParseException {
		
		String responseBody = get(apiUrl,requestHeaders);

        System.out.println(responseBody);

    	JSONParser jsonParser = new JSONParser();
    	JSONObject jsonObject = (JSONObject)jsonParser.parse(responseBody);
    	JSONArray list = (JSONArray)jsonObject.get("results");
    	
    	System.out.println(list);
    	
    	List<MovieVO> results = new ArrayList<MovieVO>();
    	
    	for (int i=0; i<list.size(); i++) {
    		
    		MovieVO vo = new MovieVO();
            
            jsonObject = (JSONObject)list.get(i);
            
            vo.setBackdropPath((String)jsonObject.get("backdrop_path"));
            vo.setId((Long)jsonObject.get("id"));
            vo.setOverview((String)jsonObject.get("overview"));
            String posterPath = "https://image.tmdb.org/t/p/w92";
            vo.setPosterPath(posterPath+(String)jsonObject.get("poster_path"));
            vo.setTitle((String)jsonObject.get("title"));
            
            results.add(vo);
            
    	}
		
		
		return results;
		
	}
	
    private String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
    
    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }
    
    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }

	

}
