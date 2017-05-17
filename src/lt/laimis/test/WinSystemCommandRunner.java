package lt.laimis.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WinSystemCommandRunner {

	public static boolean printOut = false;

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
		
		runSystemCommand("notepad.exe C:/Users/laimonas.milasius/Downloads/iQClient/configuration/ATMiQClient.cfg");

		//runSystemCommand("C:/Users/laimonas.milasius/Downloads/iQClient/iQClient.exe");
		
	}
}
