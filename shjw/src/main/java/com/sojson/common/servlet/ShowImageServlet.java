package com.sojson.common.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author xj
 * 
 */
public class ShowImageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException {
		String static_server_path = "D:\\shjw\\photo\\";
		String imgId = httpServletRequest.getParameter("imgId");
		String[] imgids = imgId.split("_");
		if (imgids.length > 1) {
			File filePic = new File(static_server_path + imgids[0]+"\\"+imgids[1]);
			System.out.println(static_server_path + imgids[0]+"\\"+imgids[1]);
			if (filePic.exists()) {
				System.out.println("图片文件存在，开始读取");
				FileInputStream is = new FileInputStream(filePic);

				httpServletResponse.reset();
				httpServletResponse.setHeader("Cache-Control", "no-store");
				httpServletResponse.setHeader("Pragma", "no-cache");
				httpServletResponse.setDateHeader("Expires", 0);
				httpServletResponse.setHeader("Content-Disposition",
						"attachment;filename="
								+ new String((getImageName(imgId)).getBytes(),
										"iso-8859-1"));
				httpServletResponse.setContentType("charset=utf-8");
				ServletOutputStream out = httpServletResponse.getOutputStream();
				BufferedInputStream bis = null;
				BufferedOutputStream bos = null;
				try {
					bis = new BufferedInputStream(is);
					bos = new BufferedOutputStream(out);
					byte[] buff = new byte[2048];
					int bytesRead;
					while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
						bos.write(buff, 0, bytesRead);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bis != null)
						bis.close();
					if (bos != null)
						bos.close();
				}
			}
		}
	}

	public static String getImageName(String originalName) {
		String[] arrar = originalName.split("\\/");
		return arrar[arrar.length - 1];
	}
public static void main(String[] args) {
	String ss="20171026_weqwr.jpg";
	String[]sss=ss.split("\\_");
	System.out.println(sss[0]+sss[1]);
}
}
