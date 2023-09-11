package insta.boost;

public class ResultModel {
	int appServiceId;
	String appServiceName;
	int AppPrice;
	Double smmPanelPrice;
	int smmServiceId;

	public ResultModel(int vappServiceId, int vAppPrice, Double vsmmPanelPrice, int vsmmServiceId,
			String vAppServiceName) {
		this.appServiceId = vappServiceId;
		this.AppPrice = vAppPrice;
		this.smmPanelPrice = vsmmPanelPrice;
		this.smmServiceId = vsmmServiceId;
		this.appServiceName = vAppServiceName;

	}

	public int getServiceId() {
		return appServiceId;
	}

	public void setServiceId(int appServiceId) {
		this.appServiceId = appServiceId;
	}

	public int getServiceRate() {
		return AppPrice;
	}

	public void setServiceRate(int serviceRate) {
		this.AppPrice = serviceRate;
	}

	public Double getPanelRate() {
		return smmPanelPrice;
	}

	public void setPanelRate(Double panelRate) {
		this.smmPanelPrice = panelRate;
	}

	@Override
	public String toString() {
		return "\n" + "App Service ID - " + appServiceId + ",\nSmm Panel Service ID - " + smmServiceId
				+ ",\nApp Price - " + AppPrice + ",\nPanel Price - " + String.format("%.2f", smmPanelPrice)
				+ "\n--------------------------";
	}

}
