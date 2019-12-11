package com.reyco.kn.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Test {
	public static void main(String[] args) {
		client();
	}

	public static void getConnect() {
		Test ftp = new Test();
		try {
			JSch jsch = new JSch();

			// 获取sshSession 账号-ip-端口
			Session sshSession = jsch.getSession("pingan", "47.111.126.62", 22);
			// 添加密码
			sshSession.setPassword("123456");
			Properties sshConfig = new Properties();
			// 严格主机密钥检查
			sshConfig.put("StrictHostKeyChecking", "no");

			sshSession.setConfig(sshConfig);
			// 开启sshSession链接
			sshSession.connect();
			// 获取sftp通道
			Channel channel = sshSession.openChannel("sftp");
			// 开启
			channel.connect();
			ChannelSftp sftp = (ChannelSftp) channel;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void client() {
		FTPClient fc = null;
		try {
			//创建ftp客户端
			fc = new FTPClient();
			//设置连接地址和端口
			fc.connect("47.111.126.62", 21);
			//设置用户和密码
			fc.login("pingan", "123456");
			//设置文件类型
			fc.setFileType(FTP.BINARY_FILE_TYPE);
			//上传
			boolean flag = fc.storeFile("new.jpg", new FileInputStream(new File("D:/qrCode.jpg")));
			if(flag)
				System.out.println("上传成功...");
			else
				System.out.println("上传失败...");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//关闭连接
			if(null != fc) {
				try {
					fc.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
}
