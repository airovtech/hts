package net.smartworks.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LogUtil {

	public LogUtil() {
		super();
	}
	
	public static void makeFileFromException(Exception e, String description, String fileNameAppend) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String nowStr = sdf.format(now);
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		
		try {
			FileUtil.writeString(PropertiesUtil.getInstance().getMail_log_folder()+"htc_mail_"+nowStr+"_"+now.getTime()+"_"+fileNameAppend+".txt", sw.toString()+"\n"+ description, true);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public static void makeLogFile(String fileName, String contents) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String nowStr = sdf.format(now);
		
		try {
			FileUtil.writeString(PropertiesUtil.getInstance().getMail_log_folder() + fileName+".txt", contents, true);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
