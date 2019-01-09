package com.file.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.file.utils.FileUtils;

public class FileTest {

	public static void main(String[] args) {
		try {
			File file = new File("F:/1.pdm");

			if (!file.exists()) {
				// 判断文件上一级是否有文件夹，没有文件夹，先创建文件夹
				if (!file.getParentFile().exists()) {
					// 按照路径创建所有文件夹
					file.getParentFile().mkdirs();
				}
				// 创建文件
				file.createNewFile();
			}

			FileInputStream fis = new FileInputStream(file);

			urlmethod(file, fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void urlmethod(File file, FileInputStream fis) {
		HttpURLConnection conn = null;
        PrintWriter pw = null ;
        BufferedReader rd = null ;
        StringBuilder sb = new StringBuilder ();
        String line = null ;
        String response = null;
		try {
			conn = (HttpURLConnection) new URL("http://localhost:8080/file/upload.do").openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(20000);
            conn.setConnectTimeout(20000);
            conn.setUseCaches(false);
            conn.connect();
            pw = new PrintWriter(conn.getOutputStream());
            pw.print("filename="+file.getName()+"&fileinfo="+FileUtils.encodeBase64File(fis));
            pw.flush();
            rd  = new BufferedReader( new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while ((line = rd.readLine()) != null ) {
                sb.append(line);
            }
            response = sb.toString();
            System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
