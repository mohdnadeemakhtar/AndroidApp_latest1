package de.seco.bloxxapp.general;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import de.seco.bloxx.dataaccess.RestClientImpl;
import de.seco.bloxxapp.activities.HomeWallActivity;
import de.seco.bloxxapp.activities.LoginActivity;
import de.seco.bloxxapp.domain.BloxxUser;

public class LoginTask extends AsyncTask<Void, Void, Void> {
	
	
	private LoginActivity loginActivity;
    private String loginData;
    
	public LoginTask(final LoginActivity loginActivity) {
		this.loginActivity = loginActivity;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		try {
			loginAndSaveBloxxUser();
			
//			loginData = getJSONfromURL("http://10.0.2.2:8080/mobile/getUserWall");
//			Log.e("LoginData  :", loginData);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
    protected void onPostExecute(Void param) {
		if (BloxxResources.myBloxxUser == null) {
			Toast.makeText(loginActivity, "Login fehlgeschlagen", Toast.LENGTH_SHORT).show();
			return;
		}
		
		Intent intent = new Intent(loginActivity, HomeWallActivity.class);
		
		loginActivity.startActivity(intent);
    }
	
	private void loginAndSaveBloxxUser() throws Exception {
		RestClientImpl<BloxxUser> restClientImpl = new RestClientImpl<BloxxUser>(BloxxResources.LOGIN_URI);			
		restClientImpl.addKeyValueParam("email", "nadeem.akhtar@bloxx.com");
		restClientImpl.addKeyValueParam("password", "password");
		BloxxResources.myBloxxUser = (BloxxUser) restClientImpl.sendRequestAndMarshallResult(BloxxUser.class);
	}
	
	
	
	public String getJSONfromURL(String url) throws Exception {
		InputStream is = null;
		String result = "";

		try {

			// HttpGet httpGet = new HttpGet(url);
			HttpPost httpGet = new HttpPost(url);

			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
			nameValuePair.add(new BasicNameValuePair("bloxxUser", "21"));
			httpGet.setEntity(new UrlEncodedFormEntity(nameValuePair));

			HttpParams httpParameters = new BasicHttpParams();
			DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
			// httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, PROXY_HOST);
			BasicHttpResponse httpResponse = (BasicHttpResponse) httpClient
					.execute(httpGet);

			HttpEntity entity = httpResponse.getEntity();

			is = entity.getContent();

		} catch (Exception e) {
			throw e;
		}

		// convert response to string
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			throw e;
		}

		return result;
	}
}