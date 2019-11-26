package com.reyco.kn.core.utils;

import java.util.Random;

public class CaptchaUtils {
	
	/**
	 * 生成验证码
	 * @return
	 */
	public static String createCode() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int key = random.nextInt(3);
			switch (key) {
			case 0:
				int code1 = random.nextInt(10);
				sb.append(code1);
				break;
			case 1:
				char code2 = (char) (random.nextInt(26) + 65);
				sb.append(code2);
				break;
			case 2:
				char code3 = (char) (random.nextInt(26) + 97);
				sb.append(code3);
				break;
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String verifyCode = createCode();
		System.out.println(verifyCode);
	}
}
