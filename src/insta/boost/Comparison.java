package insta.boost;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Comparison {

	static double exchangeRate;
	static ArrayList<ServiceResponse> serviceResponseList;
	static ArrayList<ResultModel> comparisonList = new ArrayList<ResultModel>();
	static String botToken = "5644977891:AAGYiDBwBeBNL3r5gSQ3JCmZQSULTm0DzhM";
	static long chatId = -4003148302L;
	static String parseMode = "HTML";

		public static void main(String[] args) throws IOException {

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonResponse = objectMapper.readTree(getAppServices());
			serviceResponseList = objectMapper.readValue(jsonResponse.toString(),
					objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, ServiceResponse.class));
			if (!serviceResponseList.isEmpty()) {
				exchangeRate = makeExchangeApiCall();
			}
			if (!serviceResponseList.isEmpty()) {
				for (ServiceResponse serviceResponse : serviceResponseList) {
					int panelNum = serviceResponse.getServedByPanel();
					int panelServiceNum = serviceResponse.getSmmPanelServiceId();
					SmmPanel smmDetail = getPanelMap().get(panelNum);
					ObjectMapper panelObjectMapper = new ObjectMapper();
					JsonNode panelResponseJson = panelObjectMapper
							.readTree(performPanelApiCall(smmDetail.getBaseUrl(), smmDetail.getApiKey()));
					ArrayList<PanelResponse> panelResponseList = panelObjectMapper
							.readValue(panelResponseJson.toString(), panelObjectMapper.getTypeFactory()
									.constructCollectionType(ArrayList.class, PanelResponse.class));
					for (PanelResponse panelResponse : panelResponseList) {
						if (panelResponse.getService() == panelServiceNum) {
							Double inrRate = panelResponse.getRate() * exchangeRate;
							comparisonList.add(new ResultModel(serviceResponse.getId(), serviceResponse.getRate(),
									inrRate, panelResponse.getService(), serviceResponse.getName()));
						}
					}
				}
			}

			Iterator<ResultModel> iterator = comparisonList.iterator();
			List<ResultModel> batch = new ArrayList<>();
			while (iterator.hasNext()) {
				batch.add(iterator.next());
				if (batch.size() == 4 || (!iterator.hasNext() && !batch.isEmpty())) {
					StringBuilder text = new StringBuilder();
					for (ResultModel result : batch) {
						String resultText = result.toString();
						text.append(resultText);
					}
					sendMessage(botToken, chatId, text.toString(), parseMode);
					batch.clear();
				}
			}
		}

	public static String getAppServices() {
		try {
			String url = "https://igboosts.info/smm-insta/services/get-all-enabled-services";
			URL apiUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
			connection.setRequestMethod("GET");
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			connection.disconnect();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

		public static Double makeExchangeApiCall() throws IOException {
			String secondApiUrl = "https://www.xe.com/api/protected/midmarket-converter/";
			URL secondApiUrlObj = new URL(secondApiUrl);
			HttpURLConnection secondApiConnection = (HttpURLConnection) secondApiUrlObj.openConnection();
			try {
				secondApiConnection.setRequestMethod("GET");
			} catch (ProtocolException e) {
				e.printStackTrace();
			}
			secondApiConnection.setRequestProperty("authorization",
					"Basic bG9kZXN0YXI6dUJ5RG5NdVJkTnplSzgzVTh1a0R2b1JZa2c0dnFZRUU=");
			BufferedReader secondApiReader = new BufferedReader(
					new InputStreamReader(secondApiConnection.getInputStream()));
			String secondApiResponseLine;
			StringBuilder secondApiResponse = new StringBuilder();

			while ((secondApiResponseLine = secondApiReader.readLine()) != null) {
				secondApiResponse.append(secondApiResponseLine);
			}
			secondApiReader.close();
			secondApiConnection.disconnect();
			ObjectMapper secondApiResponseMapper = new ObjectMapper();
			JsonNode secondApiResponseJson = secondApiResponseMapper.readTree(secondApiResponse.toString());

			double inrValue = secondApiResponseJson.get("rates").get("INR").asDouble();

			System.out.println("Value for INR: " + inrValue);
			return inrValue;

		}
	  
		public static Map<Integer, SmmPanel> getPanelMap() {
			Map<Integer, SmmPanel> panelMap = new HashMap<>();
			panelMap.put(AppConstants.PANEL.HONEST_SMM_ANKUSH2015, new SmmPanel(AppConstants.PANEL.HONEST_SMM_BASE_URL,
					AppConstants.PANEL.HONEST_SMM_ANKUSH2015_API_KEY));
			panelMap.put(AppConstants.PANEL.REALSITE_ANKUSH2015,
					new SmmPanel(AppConstants.PANEL.REALSITE_BASE_URL, AppConstants.PANEL.REALSITE_ANKUSH2015_API_KEY));
			panelMap.put(AppConstants.PANEL.HONEST_SMM_ANKUSH2015TEST, new SmmPanel(
					AppConstants.PANEL.HONEST_SMM_BASE_URL, AppConstants.PANEL.HONEST_SMM_ANKUSH2015TEST_API_KEY));
			panelMap.put(AppConstants.PANEL.HONEST_SMM_TUBEBOOST, new SmmPanel(AppConstants.PANEL.HONEST_SMM_BASE_URL,
					AppConstants.PANEL.HONEST_SMM_TUBEBOOST_API_KEY));
			panelMap.put(AppConstants.PANEL.SMM_SURGE_TUBEBOOST, new SmmPanel(AppConstants.PANEL.SMM_SURGE_BASE_URL,
					AppConstants.PANEL.SMM_SURGE_TUBEBOOST_API_KEY));
			panelMap.put(AppConstants.PANEL.SMM_BIRLA_TUBEBOOST, new SmmPanel(AppConstants.PANEL.SMM_BIRLA_BASE_URL,
					AppConstants.PANEL.SMM_BIRLA_TUBEBOOST_API_KEY));
			panelMap.put(AppConstants.PANEL.SMM_OWL_TUBEBOOST,
					new SmmPanel(AppConstants.PANEL.SMM_OWL_BASE_URL, AppConstants.PANEL.SMM_OWL_TUBEBOOST_API_KEY));
			return panelMap;
		}
	  
	public static String performPanelApiCall(String baseUrl, String apiKey) {
		try {
			StringBuilder urlBuilder = new StringBuilder(baseUrl);
			urlBuilder.append("?key=").append(apiKey);
			urlBuilder.append("&action=services");
			String apiUrl = urlBuilder.toString();
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			connection.disconnect();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void sendMessage(String botToken, long chatId, String text, String parseMode) throws IOException {
		try {
			String apiUrl = "https://api.telegram.org/bot" + botToken + "/sendMessage";
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			String parameters = "chat_id=" + chatId + "&text=" + text + "&parse_mode=" + parseMode;
			byte[] postData = parameters.getBytes(StandardCharsets.UTF_8);
			connection.setRequestProperty("Content-Length", String.valueOf(postData.length));
			try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
				outputStream.write(postData);
			}
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
			} else {
				System.err.println("API Request failed with HTTP error code: " + responseCode);
			}
			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
