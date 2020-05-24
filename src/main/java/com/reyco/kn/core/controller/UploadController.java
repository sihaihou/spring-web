package com.reyco.kn.core.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.reyco.kn.core.domain.BaseEntity;
import com.reyco.kn.core.utils.PoiExportUtil;
import com.reyco.kn.core.utils.PoiUtil;
import com.reyco.kn.core.utils.R;

import net.coobird.thumbnailator.Thumbnails;

@RestController
@RequestMapping("/api")
public class UploadController {

	private String serverurl = "http://localhost:8081/upload/images/";
	
	//private String serverurl = "http://www.housihai.com/upload/images/";

	/**
	 * 上传裁剪图片
	 * 
	 * @throws IOException
	 */
	@PostMapping("/upload")
	public String uploadImage(
			@RequestParam(value = "x", required = false) Integer x,
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
		Thumbnails.of(tempFile).sourceRegion(x, y, width, height).size(width, height).keepAspectRatio(true).toFile(tempFile);
		// 获取新图片地址
		String imgUrl = serverurl + fileName;
		return R.successToJson(imgUrl, "成功");
	}

	@RequestMapping("excel")
	public String excel(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws IOException, InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		List<BaseEntity> bases = PoiUtil.excelDataForkJoinTask(file);
		long end = System.currentTimeMillis();
		System.out.println(bases.size()+","+(end-start));
		return bases.size()+","+(end-start);
	}
	
	@RequestMapping("export")
	public void export(HttpServletResponse response) throws Exception {
		String fileName = "报表";
		Workbook wb = new  HSSFWorkbook();
		String[] columnNames = new String[] {"名称","备注"};
		String[] keys = new String[] {"name","desc"};
		List<BaseEntity> list = getList();
		List<Map<String, Object>> entitys = getListMap(list);
		PoiUtil.excelData(entitys,wb,keys,columnNames);
		PoiExportUtil.export(response, fileName, wb);
	}
	private List<BaseEntity> getList(){
		List<BaseEntity> list = new  ArrayList<BaseEntity>();
		for(int i=0;i<10;i++) {
			BaseEntity baseEntity = new BaseEntity();
			baseEntity.setName("name"+i);
			baseEntity.setDesc("备注"+getRandom(10));
			list.add(baseEntity);
		}
		return list;
	}
	
	private List<Map<String, Object>> getListMap(List<BaseEntity> baseEntity){
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < baseEntity.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", baseEntity.get(i).getName());
			map.put("desc", baseEntity.get(i).getDesc());
			listmap.add(map);
		}
		return listmap;
	}
	
	public static Integer getRandom(Integer num) {
		Random random = new Random();
		int nextInt = random.nextInt(num);
        return nextInt;
	}
	
}
