package com.example.ccc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class Main {

	/**
	 * Strip control characters from files from inputFolder, saves them in outputFolder
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		if (args.length < 2) {
			System.out.println("Try:");
			System.out
					.println("java -jar ccc.jar inputFileOrFolderPath outputFileOrFolderPath");
			System.exit(-1);
		}

		String inputFileFolderPath = args[0];
		String outputFileFolderPath = args[1];

		
		
		File inputFile = new File(inputFileFolderPath);
		File outputFile = new File(outputFileFolderPath);

		if (inputFile.isDirectory() && outputFile.isDirectory()) {

			File outputDir = outputFile;
			File inputDir = inputFile;

			File[] files = inputDir.listFiles(new FilenameFilter() {

				public boolean accept(File dir, String name) {
					if (name.startsWith(".")) {
						return false;
					}
					return true;
				}
			});

			for (File f : files) {
				FileInputStream fisTargetFile = new FileInputStream(f);
				String inputString = IOUtils.toString(fisTargetFile, "UTF-8");
				
				String outputString = stripControlChars(inputString);
				
				
				FileOutputStream fos = new FileOutputStream(new File(outputDir,f.getName()));
				IOUtils.write(outputString, fos);
				
				fisTargetFile.close();
				fos.close();
			}

		}else{
			System.out.println("Currently only folders are supported !");
			System.exit(-1);
		}
	}

	/**
	 * Function to strip control characters from a string. Any character below a
	 * space will be stripped from the string.
	 * 
	 * @param iString
	 *            the input string to be stripped.
	 * @return a string containing the characters from iString minus any control
	 *         characters.
	 */
	public static String stripControlChars(String iString) {
		StringBuffer result = new StringBuffer(iString);
		int idx = result.length();
		while (idx-- > 0) {
			if (result.charAt(idx) < 0x20 && result.charAt(idx) != 0x9
					&& result.charAt(idx) != 0xA && result.charAt(idx) != 0xD) {
				System.out.println("deleted character at: " + idx);
				result.deleteCharAt(idx);
			}
		}
		return result.toString();
	}

}
