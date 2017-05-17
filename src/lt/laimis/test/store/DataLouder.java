package lt.laimis.test.store;

import java.io.File;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import lt.laimis.test.FileReadWrite;

public class DataLouder {

	// String xmlStore =
	// "D:/Projects/Payments.IQ/workspace.client/JBossValidator/"
	// + "src/lt/laimis/test/store.xml";
	// H:\git\LibraryWSMonitor
	// String xmlStore =
	// "C:/git/JBossValidator/src/lt/laimis/test/store/store.xml";
	//public static String xmlStore = "H:/git/LibraryWSMonitor/src/lt/laimis/test/store/store.xml";
	public static String xmlStore = "D:/Projects/Payments.IQ/repositories/JBossValidator/src/lt/laimis/test/store/store.xml";
	
	public StorageRoot initData(Shell dialog) {

		StorageRoot storageRoot = new StorageRoot();

		try {

			JAXBContext jaxbContext = JAXBContext
					.newInstance(StorageRoot.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			storageRoot = (StorageRoot) jaxbUnmarshaller.unmarshal(new File(
					xmlStore));

		} catch (Exception e) {

			if (e.getCause().toString().contains("FileNotFoundException")) {

				FileDialog fileDialog = new FileDialog(
						dialog.getShell());

				Path currentRelativePath = Paths.get("");
				String sDir = currentRelativePath.toAbsolutePath().toString();
				System.out.println("Current relative path is: " + sDir);

				fileDialog.setFilterPath(sDir);

				String result = fileDialog.open();

				if (result != null) {

					xmlStore = result;
				}

			}
		}

		return storageRoot;
	}

	public StorageRoot initData() {
		return initData(null);
	}

	public String saveData(StorageRoot storageRoot) {

		String result = "Duomenys issaugoti sekmingai";

		try {

			JAXBContext jaxbContext = JAXBContext
					.newInstance(StorageRoot.class);

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
