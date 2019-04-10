package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class JSONParserServlet
 */
@WebServlet("/JSONParserServlet")
public class JSONParserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSONParserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 try {
			 ArrayList<CountryBean> list=new ArrayList<CountryBean>();
	            
	        	String txt=readJsonFromUrll("http://www.androidbegin.com/tutorial/jsonparsetutorial.txt");
	        	
	        	JSONObject jo=new JSONObject(txt);
	        	/*JSONArray json = readJsonFromUrl("http://www.androidbegin.com/tutorial/jsonparsetutorial.txt");
	            JSONArray jsonarray = json.getJSONObject("")
	            */
	        	JSONArray jsonarray = jo.getJSONArray("worldpopulation");
	        	
	        	for(int i=0;i<jsonarray.length();i++)
	            {
        	     CountryBean c=new CountryBean();
	        	JSONObject obj = (JSONObject)jsonarray.get(i);
	            c.setRank((int)obj.get("rank"));
	           c.setCountry((String)obj.get("country"));
	           c.setPopulation((String)obj.get("population"));
	            c.setFlag((String)obj.get("flag"));
	            list.add(c);
	            }
	            
	        	RequestDispatcher rd=request.getRequestDispatcher("show.jsp");
	        	request.setAttribute("LIST", list);
	        	rd.forward(request,response);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	}
		 
		   public static String readAll(Reader rd) throws IOException {
		        StringBuilder sb = new StringBuilder();
		        int cp;
		        while ((cp = rd.read()) != -1) {
		            sb.append((char) cp);
		        }
		        return sb.toString();
		    }

		    public static JSONArray readJsonFromUrl(String url) throws IOException {
		        // String s = URLEncoder.encode(url, "UTF-8");
		        // URL url = new URL(s);
		        InputStream is = new URL(url).openStream();
		        JSONArray json = null;
		        try {
		            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		            String jsonText = readAll(rd);
		            json = new JSONArray(jsonText);
		        } catch (JSONException e) {
		            e.printStackTrace();
		        } finally {
		            is.close();
		        }
		        return json;
		    }
		    public static String readJsonFromUrll(String url) throws IOException {
		        // String s = URLEncoder.encode(url, "UTF-8");
		        // URL url = new URL(s);
		        InputStream is = new URL(url).openStream();
		        JSONArray json = null;
		        String jsonText ="";
		        try {
		            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		           jsonText = readAll(rd);
		           // json = new JSONArray(jsonText);
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            is.close();
		        }
		        return jsonText;
		    }
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	}
