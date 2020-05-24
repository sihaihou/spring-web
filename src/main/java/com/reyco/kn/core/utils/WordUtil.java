package com.reyco.kn.core.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class WordUtil {
	/**
	 * 
	 * @param map        		对象数据集
	 * @param out        		输出流
	 * @param name				模板文件名称
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void exportWord(Map<String, Object> map, OutputStream out, String name)
			throws IOException, TemplateException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
		// String templatePath =PAWordUtils.class.getResource("/").getPath()+ "/ftl";
		// cfg.setDirectoryForTemplateLoading(new File(templatePath));
		cfg.setClassForTemplateLoading(WordUtil.class, "/ftl");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		cfg.setWrapUncheckedExceptions(true);
		Template tmpl = cfg.getTemplate(name);
		Writer writer = new OutputStreamWriter(out, "utf-8");
		tmpl.process(map, writer);
		writer.flush();
		writer.close();
	}
}
