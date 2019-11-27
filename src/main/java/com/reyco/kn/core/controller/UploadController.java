package com.reyco.kn.core.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.reyco.kn.core.utils.R;

import net.coobird.thumbnailator.Thumbnails;

@RestController
@RequestMapping("/api")
public class UploadController {

	private String serverurl = "http://localhost:8081/upload/images/";

	/**
	 * 上传裁剪图片
	 * 
	 * @throws IOException
	 */
	@PostMapping("/upload")
	public String uploadImage(@RequestParam(value = "x", required = false) Integer x,
			@RequestParam(value = "y", required = false) Integer y,
			@RequestParam(value = "height", required = false) int height,
			@RequestParam(value = "width", required = false) int width,
			@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws IOException {
		// 接收图片
		if (file.isEmpty()) {
			return R.failToJson("图片不能为空...", "图片为空...");
		}
		// 保存图片
		String realPath = request.getServletContext().getRealPath("/");
		String fileName = System.currentTimeMillis() + file.getOriginalFilename();
		String resourcePath = "upload" + File.separator + "images" + File.separator;
		File tempFile = new File(realPath + resourcePath, fileName);
		FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
		// 实现图片裁剪
		Thumbnails.of(tempFile).sourceRegion(x, y, width, height).size(width, height).keepAspectRatio(false).toFile(tempFile);
		// 获取新图片地址
		String imgUrl = serverurl + fileName;
		return R.successToJson(imgUrl, "成功");
	}

}
