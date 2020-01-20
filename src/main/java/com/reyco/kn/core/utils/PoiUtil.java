package com.reyco.kn.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RecursiveTask;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.reyco.kn.core.domain.BaseEntity;

@SuppressWarnings("all")
public class PoiUtil {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	/*
	 * getLastRowNum:       * 如果sheet中一行数据都没有则返回-1，只有第一行有数据则返回0，最后有数据的行是第n行则返回 n-1；
	 * getLastCellNum        * 如果row中一列数据都没有则返回-1，只有第一列有数据则返回1，最后有数据的列是第n列则返回 n；
	 * getPhysicalNumberOfRows       * 获取有记录的行数，即：最后有数据的行是第n行，前面有m行是空行没数据，则返回n-m；
	 * getPhysicalNumberOfCells        * 获取有记录的列数，即：最后有数据的列是第n列，前面有m列是空列没数据，则返回n-m；
	 */

	/**
	 * 将数据写入到表格文件
	 * 
	 * @param entitys
	 *            select的数据
	 * @param wb
	 *            表格
	 * @param keys
	 * @param columnNames
	 *            列名
	 */
	public static void excelData(List<Map<String, Object>> entitys, Workbook wb, String[] keys, String[] columnNames) {
		int rowIndex = 0;
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(rowIndex++);
		for (int i = 0; i < columnNames.length; i++) {
			row.createCell(i).setCellValue(columnNames[i]);
		}
		for (int i = 0; i < entitys.size(); i++) {
			row = sheet.createRow(i + 1);
			System.out.println("row=" + row + ",i" + i);
			for (int j = 0; j < keys.length; j++) {
				System.out.println("keys=" + keys + ",j" + j);
				row.createCell(j).setCellValue(entitys.get(i).get(keys[j]).toString());
			}
		}
	}

	/**
	 * 读取模板文件 将数据写入到模板文件
	 * 
	 * @param list
	 *            select的数据
	 * @param keys
	 *            列名
	 * @param fileName
	 *            模板文件名
	 * @return
	 * @throws Exception
	 */
	public static Workbook excelTemplate(List<Map<String, Object>> list, String[] keys, String fileName)
			throws Exception {
		InputStream is = PoiUtil.class.getResourceAsStream(fileName);
		POIFSFileSystem poi = new POIFSFileSystem(is);
		Workbook wb = new HSSFWorkbook(poi);
		Sheet sheet = wb.getSheetAt(0);
		int cellNums = sheet.getRow(0).getLastCellNum();
		for (int i = 1; i < list.size(); i++) {
			Row row = sheet.createRow(i);
			for (int j = 0; j < cellNums; j++) {
				row.createCell(j).setCellValue(list.get(i).get(keys[j]).toString());
			}
		}
		return wb;
	}

	/**
	 * 获取HSSFCell类型
	 * 
	 * @param cell
	 * @return
	 */
	public static String formatCell(HSSFCell cell) {
		// 如果cell为null直接return
		if (null == cell) {
			return "";
		} else {
			// 设置所有单元格为String类型
			cell.setCellType(CellType.STRING);
			// 获取String类型单元格内容
			return String.valueOf(cell.getStringCellValue());
		}
	}

	/**
	 * 获取hssfcell单元格格式内容
	 * 
	 * @param hssfCell
	 * @return
	 */
	public static String getHSSFCellValue(HSSFCell hssfcell) {
		if (hssfcell.getCellType() == CellType.BOOLEAN) {
			return String.valueOf(hssfcell.getBooleanCellValue());
		} else if (hssfcell.getCellType() == CellType.NUMERIC) {
			String cellValue = "";
			if (HSSFDateUtil.isCellDateFormatted(hssfcell)) {
				Date date = HSSFDateUtil.getJavaDate(hssfcell.getNumericCellValue());
				cellValue = sdf.format(date);
			} else {
				DecimalFormat df = new DecimalFormat("#.##");
				cellValue = df.format(hssfcell.getNumericCellValue());
				String strArr = cellValue.substring(cellValue.lastIndexOf("") + 1, cellValue.length());
				if (strArr.equals("00")) {
					cellValue = cellValue.substring(0, cellValue.lastIndexOf("."));
				}
			}
			return cellValue;
		} else if (hssfcell.getCellType() == CellType.ERROR) {
			return "";
		} else {
			return String.valueOf(hssfcell.getStringCellValue());
		}
	}

	/**
	 * 获取XSSFCell单元格格式的内容
	 * 
	 * @param xssfCell
	 * @return
	 */
	public static String getXSSFCellValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == CellType.BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == CellType.NUMERIC) {
			String cellValue = "";
			DecimalFormat df = new DecimalFormat("#.##");
			cellValue = df.format(xssfCell.getNumericCellValue());
			String strArr = cellValue.substring(cellValue.lastIndexOf(".") + 1, cellValue.length());
			if (strArr.equals("00")) {
				cellValue = cellValue.substring(0, cellValue.lastIndexOf("."));
			}
			return cellValue;
		} else if (xssfCell.getCellType() == CellType.ERROR) {
			return "";
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}

	/**
	 * 
	 * @param file
	 * @throws IOException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static List<BaseEntity> excelDataForkJoinTask(MultipartFile file)
			throws IOException, InterruptedException, ExecutionException {
		InputStream is = file.getInputStream();
		HSSFWorkbook wb = new HSSFWorkbook(is);
		List<BaseEntity> bases = new ArrayList<BaseEntity>();
		// 循环遍历sheet
		for(int i=0;i<wb.getNumberOfSheets();i++) {
			HSSFSheet sheet = wb.getSheetAt(i);
			if(null == sheet) {
				continue;
			}
			int lastRowNum = sheet.getLastRowNum() + 1;
			ForkJoinPool pool = new ForkJoinPool();
			AddTask task = new AddTask(0, lastRowNum, sheet);
			ForkJoinTask<List<BaseEntity>> future = pool.submit(task);
			List<BaseEntity> list = future.get();
			bases.addAll(list);
		}
		return bases;
	}

	/**
	 * 
	 * @param file
	 * @throws IOException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static List<BaseEntity> excelDataForkJoinAndFutureTask(MultipartFile file)
			throws IOException, InterruptedException, ExecutionException {
		InputStream is = file.getInputStream();
		HSSFWorkbook wb = new HSSFWorkbook(is);
		List<BaseEntity> bases = new ArrayList<BaseEntity>();
		// 循环遍历sheet
		for (Sheet sheet : wb) {
			Callable<List<BaseEntity>> task = new Callable<List<BaseEntity>>() {
				@Override
				public List<BaseEntity> call() throws Exception {
					List<BaseEntity> bases = new ArrayList<BaseEntity>();
					int lastRowNum = sheet.getLastRowNum() + 1;
					ForkJoinPool pool = new ForkJoinPool();
					PoiUtil.AddTask testTask1 = new PoiUtil.AddTask(0, lastRowNum, sheet);
					ForkJoinTask<List<BaseEntity>> future = pool.submit(testTask1);
					List<BaseEntity> list = future.get();
					return list;
				}
			};
			FutureTask<List<BaseEntity>> futureTask = new FutureTask<>(task);
			new Thread(futureTask).start();
			List<BaseEntity> list = futureTask.get();
			bases.addAll(list);
		}
		return bases;
	}

	static class AddTask extends RecursiveTask<List<BaseEntity>> {
		// 最大执行数据
		Integer maxSize = 2000;
		// 开始
		Integer begin;
		// 结束
		Integer end;

		Sheet sheet;

		public AddTask(Integer begin, Integer end, Sheet sheet) {
			this.begin = begin;
			this.end = end;
			this.sheet = sheet;
		}

		@Override
		protected List<BaseEntity> compute() {
			List<BaseEntity> bases = new ArrayList<BaseEntity>();
			if (end - begin < maxSize) {
				for (int i = begin; i < end; i++) {
					HSSFRow row = (HSSFRow) sheet.getRow(i);
					if (null == row) {
						continue;
					}
					String name = formatCell(row.getCell(1));
					String desc = formatCell(row.getCell(2));
					if (StringUtils.isBlank(name)) {
						continue;
					}
					// 如果没有指定密码，给默认密码
					if (StringUtils.isBlank(desc)) {
						desc = "备注";
					}
					BaseEntity baseEntity = new BaseEntity();
					baseEntity.setName(name);
					baseEntity.setDesc(desc);
					bases.add(baseEntity);
				}
			} else {
				int middle = begin + (end - begin) / 2;
				PoiUtil.AddTask task1 = new PoiUtil.AddTask(begin, middle, sheet);
				PoiUtil.AddTask task2 = new PoiUtil.AddTask(middle, end, sheet);
				System.out.println("forkAndJoin");
				task1.fork();
				task2.fork();
				List<BaseEntity> join1 = task1.join();
				List<BaseEntity> join2 = task2.join();
				join1.addAll(join2);
				bases.addAll(join1);
			}
			return bases;
		}
	}

}
