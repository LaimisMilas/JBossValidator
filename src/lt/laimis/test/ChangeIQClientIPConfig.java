package lt.laimis.test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChangeIQClientIPConfig", propOrder = {
    "selectedIPAddress",
    "selectedDirAsString",
    "tmpIQClientConfigFile",
    "saveIQClientConfigFile",
    "arg"
})
public class ChangeIQClientIPConfig {
	// last selected ip address
	// last selected dir as string
	// String fileTmp = Constance.iQCLient + "/ATMiQClient_TMP.cfg";
	// ${connection_ipAddress}
	// String writeTo = selectedDirString + "/configuration/ATMiQClient.cfg";
	// IP adresas pakeistas, sėkmingai
	@XmlElement
	protected String selectedIPAddress;
	@XmlElement
	protected String selectedDirAsString;
	@XmlElement
	protected String tmpIQClientConfigFile;
	@XmlElement
	protected String saveIQClientConfigFile;
	@XmlElement
	protected String arg;
	
	public String getSelectedIPAddress() {
		return selectedIPAddress;
	}
	public void setSelectedIPAddress(String selectedIPAddress) {
		this.selectedIPAddress = selectedIPAddress;
	}
	public String getSelectedDirAsString() {
		return selectedDirAsString;
	}
	public void setSelectedDirAsString(String selectedDirAsString) {
		this.selectedDirAsString = selectedDirAsString;
	}
	public String getTmpIQClientConfigFile() {
		return tmpIQClientConfigFile;
	}
	public void setTmpIQClientConfigFile(String tmpIQClientConfigFile) {
		this.tmpIQClientConfigFile = tmpIQClientConfigFile;
	}
	public String getSaveIQClientConfigFile() {
		return saveIQClientConfigFile;
	}
	public void setSaveIQClientConfigFile(String saveIQClientConfigFile) {
		this.saveIQClientConfigFile = saveIQClientConfigFile;
	}
	public String getArg() {
		return arg;
	}
	public void setArg(String arg) {
		this.arg = arg;
	}
	
	
}
