package youdao;

public class DownloadTask {
	public String url;
	public int index;
	
	public static String FOLDER;
	
	public DownloadTask(String url, int index){
		this.url = url;
		this.index = index;
	}
}
