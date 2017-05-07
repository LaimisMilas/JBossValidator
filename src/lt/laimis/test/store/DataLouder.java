package lt.laimis.test.store;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import lt.laimis.test.FileReadWrite;

public class DataLouder {

	// String xmlStore =
	// "D:/Projects/Payments.IQ/workspace.client/JBossValidator/"
	// + "src/lt/laimis/test/store.xml";

	String xmlStore = "C:/git/JBossValidator/src/lt/laimis/test/store.xml";

	public StorageRoot initData() {

		StorageRoot storageRoot = new StorageRoot();

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(StorageRoot.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			storageRoot = (StorageRoot) jaxbUnmarshaller.unmarshal(new File(xmlStore));

		} catch (JAXBException e) {
			
			e.printStackTrace();
			
		}

		return storageRoot;
	}

	public String saveData(StorageRoot storageRoot) {
		
		String result = "Duomenys issaugoti sekmingai";

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(StorageRoot.class);

			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(storageRoot, sw);
			String xmlString = sw.toString();

			FileReadWrite.writeFile(xmlStore, xmlString);

		} catch (JAXBException e) {
			
			e.printStackTrace();
			result = e.getMessage();
		}
		
		return result;

	}

	public static void main(String[] args) {
		StorageRoot storageRoot = new DataLouder().initData();
		new DataLouder().saveData(storageRoot);
	}

}
