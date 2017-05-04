package lt.laimis.test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompareClient", propOrder = {
    "clientWithNoUpdateLocation"
})
public class CompareClient {
	@XmlElement
	protected String clientWithNoUpdateLocation;

	public String getClientWithNoUpdateLocation() {
		return clientWithNoUpdateLocation;
	}

	public void setClientWithNoUpdateLocation(String clientWithNoUpdateLocation) {
		this.clientWithNoUpdateLocation = clientWithNoUpdateLocation;
	}
}
