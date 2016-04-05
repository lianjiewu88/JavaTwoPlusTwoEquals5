package Interview.wenxin;

import java.io.File;
import java.util.List;

public class ReadFile {
	// private List<File> txtFiles = new ArrayList<File>();

	public static boolean getFiles(String iniPath, String fileType, List<File> txtFiles) {

		String fileName;

		File iniFile = new File(iniPath); // the initial file path
		if (!iniFile.exists()) {
			System.out.println("输入路径" + iniPath + "有误！");
			return false;
		}
		if (iniFile.isDirectory()) { // initial path reffers to a folder
			String[] fileList = iniFile.list();

			for (int i = 0; i < fileList.length; i++) {
				File nextFile = new File(iniFile + File.separator + fileList[i]);
				if (nextFile.isDirectory()) {
					getFiles(iniFile + File.separator + fileList[i], fileType, txtFiles);
				} else {
					fileName = nextFile.getName();
					if (fileName.endsWith(fileType)) { // decide is txt file or not
						try {
							txtFiles.add(nextFile.getAbsoluteFile());
						} catch (SecurityException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} else { // initial path reffers to a file
			if (iniFile.getName().endsWith(fileType)) {

			} else {
				System.out.println("文件路径所指文件为非文本文档！");
			}
		}
		return true;
	}
}
