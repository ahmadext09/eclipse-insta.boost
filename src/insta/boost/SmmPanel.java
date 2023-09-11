package insta.boost;

public class SmmPanel {
	String baseUrl;
	String apiKey;

	public SmmPanel(String smmBaseUrl, String smmApiKey) {
		this.baseUrl = smmBaseUrl;
		this.apiKey = smmApiKey;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
