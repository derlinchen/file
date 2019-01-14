package com.file.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.file.utils.FileUtils;

@RestController
public class FileController {

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String upload(String filename, String fileinfo) {
		String rtv = FileUtils.decoderBase64File(filename, fileinfo, null);
		return rtv;
	}

	@RequestMapping(value = "uploadpath", method = RequestMethod.POST)
	public String uploadpath(String filename, String fileinfo, String filepath) {
		String rtv = FileUtils.decoderBase64File(filename, fileinfo, filepath);
		return rtv;
	}
	
}
