package lt.laimis.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileReadWrite {

	public static List<String> readFileAsLines(String filePath) {

		List<String> list = new ArrayList<String>();

		String line = null;

		BufferedReader reader = null;

		try {

			reader = new BufferedReader(new FileReader(filePath));

			while ((line = reader.readLine()) != null) {

				list.add(line);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	public static void writeAppendFile(String filePath, String text) {

		try {

			FileWriter fw = new FileWriter(filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);

			PrintWriter out = new PrintWriter(bw);

			out.println(text);
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeFile(String filePath, String text) {

		PrintWriter writer = null;

		try {

			writer = new PrintWriter(filePath, "UTF-8");
			writer.write(text);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

	public static String readFileAsString(String filePath) {

		StringBuffer stringBuffer = new StringBuffer();

		String line = null;

		BufferedReader reader = null;

		try {

			reader = new BufferedReader(new FileReader(filePath));

			while ((line = reader.readLine()) != null) {

				stringBuffer.append(line);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return stringBuffer.toString();
	}
}
