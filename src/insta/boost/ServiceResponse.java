package insta.boost;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ServiceResponse {

	private int id;
	private String name;
	private String parentCategory;
	private String parentCategoryIcon;
	private String category;
	private String categoryIconUrl;
	private String descImgUrl;
	private int rate;
	private int min;
	private int max;
	private int servedByPanel;
	private int smmPanelServiceId;
	private String howToGetLink;

	

	public ServiceResponse() {
		
	}

	
	public ServiceResponse(int id, String name, String parentCategory, String parentCategoryIcon, String category,
			String categoryIconUrl, String descImgUrl, int rate, int min, int max, int servedByPanel,
			int smmPanelServiceId, String howToGetLink) {
		this.id = id;
		this.name = name;
		this.parentCategory = parentCategory;
		this.parentCategoryIcon = parentCategoryIcon;
		this.category = category;
		this.categoryIconUrl = categoryIconUrl;
		this.descImgUrl = descImgUrl;
		this.rate = rate;
		this.min = min;
		this.max = max;
		this.servedByPanel = servedByPanel;
		this.smmPanelServiceId = smmPanelServiceId;
		this.howToGetLink = howToGetLink;
	}

	@Override
	public String toString() {
		return "ServiceResponse{" + "id=" + id + ", name='" + name + '\'' + ", parentCategory='" + parentCategory + '\''
				+ ", parentCategoryIcon='" + parentCategoryIcon + '\'' + ", category='" + category + '\''
				+ ", categoryIconUrl='" + categoryIconUrl + '\'' + ", descImgUrl='" + descImgUrl + '\'' + ", rate="
				+ rate + ", min=" + min + ", max=" + max + ", servedByPanel=" + servedByPanel + ", smmPanelServiceId="
				+ smmPanelServiceId + ", howToGetLink='" + howToGetLink + '\'' + '}';
	}

	// Add getters and setters for each field

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getParentCategoryIcon() {
		return parentCategoryIcon;
	}

	public void setParentCategoryIcon(String parentCategoryIcon) {
		this.parentCategoryIcon = parentCategoryIcon;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryIconUrl() {
		return categoryIconUrl;
	}

	public void setCategoryIconUrl(String categoryIconUrl) {
		this.categoryIconUrl = categoryIconUrl;
	}

	public String getDescImgUrl() {
		return descImgUrl;
	}

	public void setDescImgUrl(String descImgUrl) {
		this.descImgUrl = descImgUrl;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getServedByPanel() {
		return servedByPanel;
	}

	public void setServedByPanel(int servedByPanel) {
		this.servedByPanel = servedByPanel;
	}

	public int getSmmPanelServiceId() {
		return smmPanelServiceId;
	}

	public void setSmmPanelServiceId(int smmPanelServiceId) {
		this.smmPanelServiceId = smmPanelServiceId;
	}

	public String getHowToGetLink() {
		return howToGetLink;
	}

	public void setHowToGetLink(String howToGetLink) {
		this.howToGetLink = howToGetLink;
	}
}
