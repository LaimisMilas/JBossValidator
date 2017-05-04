package lt.laimis.test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"compareClient","deviceIPAddressArray","iQClientConfig"})
@XmlRootElement(name = "StorageRoot")
public class StorageRoot {
	
	private String error;

	@XmlElement(name = "CompareClient")
	protected CompareClient compareClient;
	
	@XmlElement(name = "DeviceIPAddressArray")
	protected DeviceIPAddressArray deviceIPAddressArray;

	@XmlElement(name = "ChangeIQClientIPConfig")
	protected ChangeIQClientIPConfig iQClientConfig;
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public ChangeIQClientIPConfig getiQClientConfig() {
		return iQClientConfig;
	}

	public void setiQClientConfig(ChangeIQClientIPConfig iQClientConfig) {
		this.iQClientConfig = iQClientConfig;
	}

	public DeviceIPAddressArray getDeviceIPAddressArray() {
		return deviceIPAddressArray;
	}

	public void setDeviceIPAddressArray(DeviceIPAddressArray deviceIPAddressArray) {
		this.deviceIPAddressArray = deviceIPAddressArray;
	}

	public CompareClient getCompareClient() {
		return compareClient;
	}

	public void setCompareClient(CompareClient compareClient) {
		this.compareClient = compareClient;
	}

}
