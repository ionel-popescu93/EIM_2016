package ro.pub.cs.systems.pdsd.practicaltest02.networkingthreads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ro.pub.cs.systems.pdsd.practicaltest02.general.Constants;
import ro.pub.cs.systems.pdsd.practicaltest02.general.Utilities;
import ro.pub.cs.systems.pdsd.practicaltest02.model.WeatherForecastInformation;
import android.util.Log;

public class CommunicationThread extends Thread {
	
	private ServerThread serverThread;
	private Socket       socket;
	
	public CommunicationThread(ServerThread serverThread, Socket socket) {
		this.serverThread = serverThread;
		this.socket       = socket;
	}
	
	@Override
	public void run() {
		if (socket != null) {
			try {
				BufferedReader bufferedReader = Utilities.getReader(socket);
				PrintWriter    printWriter    = Utilities.getWriter(socket);
				if (bufferedReader != null && printWriter != null) {
					Log.i(Constants.TAG, "[COMMUNICATION THREAD] Waiting for parameters from client (city / information type)!");
					String autocompleteString            = bufferedReader.readLine();


					Log.i(Constants.TAG, "[COMMUNICATION THREAD] Getting the information from the webservice...");
					HttpClient httpClient = new DefaultHttpClient();

							/*HttpPost httpPost = new HttpPost(Constants.WEB_SERVICE_ADDRESS);
							List<NameValuePair> params = new ArrayList<NameValuePair>();        
							params.add(new BasicNameValuePair(Constants.QUERY_ATTRIBUTE, city));
							UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
							httpPost.setEntity(urlEncodedFormEntity); */
					HttpGet httpGet = new HttpGet("http://autocomplete.wunderground.com/aq?query=" + autocompleteString);


					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					Log.i(Constants.TAG, "Before execute");
					String pageSourceCode = httpClient.execute(httpGet, responseHandler);
					Log.i(Constants.TAG, "After execute");
					if (pageSourceCode != null) {
						JSONObject result = new JSONObject(pageSourceCode);
						JSONArray jsonArray = result.getJSONArray("RESULTS");
						String finalResult = "";


						for (int k = 0; k < jsonArray.length(); k++) {
							JSONObject jsonObject = jsonArray.getJSONObject(k);
							finalResult += jsonObject.getString("name") + ",\n";
						}


						printWriter.println(finalResult);
						printWriter.flush();


					} else {
						Log.e(Constants.TAG, "[COMMUNICATION THREAD] Error getting the information from the webservice!");
					}
				} else {
					Log.e(Constants.TAG, "[COMMUNICATION THREAD] Weather Forecast information is null!");
				}

				socket.close();
						
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Log.e(Constants.TAG, "[COMMUNICATION THREAD] Socket is null!");
		}
	}

}
