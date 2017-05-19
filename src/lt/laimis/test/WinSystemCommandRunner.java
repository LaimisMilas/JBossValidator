package lt.laimis.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WinSystemCommandRunner {

	public static boolean printOut = false;
	
	public static void openDirectory(String path){
		
		runSystemCommand("explorer.exe " +  path);
	}
	
	public static void openFile(String path){
		
		runSystemCommand("notepad.exe " +  path);
	}

	// return String
	public static String runSystemCommand(String command) {

		StringBuffer stResponse = new StringBuffer();

		try {

			Process p = Runtime.getRuntime().exec(command);

			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(p.getInputStream()));

			String s = "";
			// reading output stream of the command
			while ((s = inputStream.readLine()) != null) {
				stResponse.append("\n " + s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (printOut) {

			System.out.print(stResponse.toString());

		}

		return stResponse.toString();
	}

	public static void main(String[] args) {
		
		//atidaro faila redagavimo rezimu
		runSystemCommand("notepad.exe C:/Users/laimonas.milasius/Downloads/iQClient/configuration/ATMiQClient.cfg");
		
		//atidaro folder
		//runSystemCommand("start .");
		
		//Paleidzia exe faila
		//runSystemCommand("C:/Users/laimonas.milasius/Downloads/iQClient/iQClient.exe");
		
	}
}
