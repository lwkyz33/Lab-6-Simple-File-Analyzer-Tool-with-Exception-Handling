package lab6;
import java.util.Scanner;
import java.io.*;

public class FileAnalyzer {
	public static void main(String [] args) {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Enter the filename: ");
			String filename = input.nextLine();
			if (filename.trim().isEmpty()) {
				throw new EmptyFilenameException("Filename cannot be empty.");
			}
			File file = new File(filename);
			if (!file.exists()) {
				throw new FileNotFoundException("File not found: " + filename);
			}
			int lineCount = 0;
			int wordCount = 0;
			int charCount = 0;
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				lineCount++;
				charCount += line.length();
				String[] words = line.trim().split("\\s+");
				if (!line.trim().isEmpty()) {
					wordCount += words.length;
				}
			}
			reader.close();
			System.out.println("\n----- File Analysis -----");
			System.out.println("Lines: " + lineCount);
			System.out.println("Words: " + wordCount);
			System.out.println("Characters: " + charCount);
			PrintWriter writer = new PrintWriter("analysis_output.txt");
			writer.println("File analysis results: ");
			writer.println("Lines: " + lineCount);
			writer.println("Words: " + wordCount);
			writer.println("Characters: " + charCount);
			writer.close();
			System.out.println("\nResults written to analysis_output.txt");
		} catch (EmptyFilenameException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found.");
		} catch (IOException e) {
			System.out.println("Error reading file.");
		} finally {
			input.close();
		}
	}
}
