package lt.laimis.test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceIPAddressArray", propOrder = {
	    "deviceIPAddresses"
	})
public class DeviceIPAddressArray {

	@XmlElement(name = "DeviceIPAddress", nillable = true)
	protected List<DeviceIPAddress> deviceIPAddresses;

	public List<DeviceIPAddress> getDeviceIPAddresses() {
		if (deviceIPAddresses == null) {
			deviceIPAddresses = new ArrayList<DeviceIPAddress>();
		}
		return this.deviceIPAddresses;
	}

}
