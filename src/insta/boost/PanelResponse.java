package insta.boost;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PanelResponse {

	private int service;
	private String name;
	private String type;
	private String category;
	private double rate;

	

	public PanelResponse() {
		
	}

	public PanelResponse(int service, String name, String type, String category, double rate) {
		this.service = service;
		this.name = name;
		this.type = type;
		this.category = category;
		this.rate = rate;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "PanelResponse{" + "service=" + service + ", name='" + name + '\'' + ", type='" + type + '\''
				+ ", category='" + category + '\'' + ", rate=" + rate + '}';
	}
}
