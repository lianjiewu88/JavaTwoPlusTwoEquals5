package gittool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://github.com/moonlightpoet/FunGithub
public class GitTool {
	
	private static Map<String, Word> wordMap = new HashMap<String, Word>();
	private static int[][] picNumber = new int[1000][30];
	public static final String PICTURE_FONT = "C:\\Users\\i042416\\git\\JavaTwoPlusTwoEquals5\\src\\gittool\\picture-fonts.txt";
	public static final String POSITION = "C:\\Users\\i042416\\git\\JavaTwoPlusTwoEquals5\\src\\gittool\\position.txt";
	
	public static void main(String[] args) {
		// System.err.println("usage : <username> <words>");
		solve("i042416", "123");
	}
	
	public static void solve(String username, String words) {
		words = words.toUpperCase();
		try (
				
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PICTURE_FONT ), "utf-8"));
				) {
			int idx = 0;
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.length() == 0) continue;
				int len = line.length();
				char[] ch = line.toCharArray();
				for (int i = 0; i < len; i ++) {
					char c = ch[i];
					picNumber[idx][i] = (int) c -  (int)'0';
				}
				idx ++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try (
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(POSITION), "utf-8"))
				) {
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.length() == 0) continue;
				Word temp = new Word(line);
				wordMap.put(temp.getC(), temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		List<Integer> allWords = new ArrayList<Integer>();
		int i;
		for (i = 0; i < words.length(); i ++) {
			String oneWordString = String.format("%c", words.charAt(i));
			Word oneWord = wordMap.get(oneWordString);
			if (oneWord == null) {
				System.err.println(oneWordString + " not exists!");
				continue;
			}
			List<Integer> tmpWordsList = oneWord.getWordsList();
			if (allWords.size() + tmpWordsList.size() > 52 * 7)
				break;
			else {
				allWords.addAll(tmpWordsList);
				if (allWords.size() + 7 <= 52 * 7)
					for (int j = 0; j < 7; j ++)
						allWords.add(0);
			}
		}
		System.out.println("\"" + words.substring(0, i) +"\" solved!");
		int delta = 52 * 7 - allWords.size();
		for (int j = 0; j < delta; j ++) allWords.add(0);
		// allWords -- the list with 52 * 7 words generated.
		
		// download url content
		URL url = null;
		HttpURLConnection urlConnection = null;
		BufferedReader reader;
		String pageContent = "";
		try {
			url = new URL("https://github.com/" + username);
			urlConnection = (HttpURLConnection) url.openConnection();
			reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
			String line;
	        while ((line = reader.readLine()) != null){
	             pageContent += line + "\n";
	        }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		pageContent = pageContent.replaceAll("fill=\"#[0-9a-e]{6}\"", "MoON1igHt");
		for (int word : allWords) {
			String fillStr = null;
			if (word == 1) {
				fillStr = "fill=\"#1e6823\"";
			} else {
				double r = Math.random();
				if (r <= 0.3) fillStr = "fill=\"#eeeeee\"";
				else if (r <= 0.9) fillStr = "fill=\"#d6e685\"";
				else if (r <= 0.96) fillStr = "fill=\"#8cc665\"";
				else fillStr = "fill=\"#44a340\"";
			}
			pageContent = pageContent.replaceFirst("MoON1igHt", fillStr);
		}
		pageContent = pageContent.replaceAll("MoON1igHt", "fill=\"#eeeeee\"");
		
		String outputFileName = "‪C:\\1.html";
		/*try (
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputFileName), "utf-8");
				) {
			osw.write(pageContent);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}*/
		
		createFile(pageContent);
		
		System.out.println(username + "-" + words + ".html successifully generated at desktop!");
	}
	
	
	static private void createFile(String fileContent){
		File file = new File("C:\\temp\\1.html");
		
	    FileOutputStream out = null;
	    try {
	        if (!file.exists()) {
	            file.createNewFile();
	        }

	        //创建文件输出流
	        out = new FileOutputStream(file);
	        //将字符串转化为字节
	        byte[] byteArr = fileContent.getBytes();
	        out.write(byteArr);
	        out.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	static class Word {
		
		private String c;
		private int px;
		private int py;
		private int height;
		private int width;
		private List<Integer> wordsList;
		
		public Word(String line) {
			String[] arr = line.split(",");
			c = arr[0];
			px = Integer.parseInt(arr[1]);
			py = Integer.parseInt(arr[2]);
			height = Integer.parseInt(arr[3]);
			width = Integer.parseInt(arr[4]);
			
			// generate wordsList
			wordsList = new ArrayList<Integer>();
			for (int j = 0; j < width; j ++) 
				for (int i = 0; i < height; i ++)
					wordsList.add(picNumber[px+i][py+j]);
		}
		
		public String getC() { return c; }
		public int getPx() { return px; }
		public int getPy() { return py; }
		public int getHeight() { return height; }
		public int getWidth() { return width; }
		public List<Integer> getWordsList() { return wordsList; }
	}
}

