package com.file.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.file.utils.FileUtils;

public class FileTest {

	public static void main(String[] args) {
		try {
			File file = new File("F:/1.png");

			if (!file.exists()) {
				// 判断文件上一级是否有文件夹，没有文件夹，先创建文件夹
				if (!file.getParentFile().exists()) {
					// 按照路径创建所有文件夹
					file.getParentFile().mkdirs();
				}
				// 创建文件
				file.createNewFile();
			}

			postmethod(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void postmethod(File file) throws Exception {
		// 创建httpclient对象
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建post方式请求对象
		HttpPost httpPost = new HttpPost("http://localhost:8080/file/upload.do");

		// 装填参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("filename", file.getName()));
		nvps.add(new BasicNameValuePair("fileinfo", FileUtils.encodeBase64File(file)));
		// 设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

		// 设置header信息
		// 指定报文头【Content-type】、【User-Agent】
		// 文件上传content-type必须设置为application/x-www-form-urlencoded
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);

		BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer sb = new StringBuffer("");
		String line = "";
		String newline = System.getProperty("line.separator");
		while ((line = in.readLine()) != null) {
			sb.append(line + newline);
		}
		in.close();
		System.out.println(sb);
	}
	
	public static void httpmethod(File file) throws Exception {
		// 创建httpclient对象
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建post方式请求对象
		HttpPost httpPost = new HttpPost("http://localhost:8080/file/upload.do");

		// 装填参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("filename", file.getName()));
		nvps.add(new BasicNameValuePair("fileinfo", FileUtils.encodeBase64File(file)));
		// 设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

		// 设置header信息
		// 指定报文头【Content-type】、【User-Agent】
		// 文件上传content-type必须设置为application/x-www-form-urlencoded
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);

		BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer sb = new StringBuffer("");
		String line = "";
		String newline = System.getProperty("line.separator");
		while ((line = in.readLine()) != null) {
			sb.append(line + newline);
		}
		in.close();
		System.out.println(sb);
	}

}
