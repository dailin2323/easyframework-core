package com.easyframework.core.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.easyframework.core.callBack.FileExportCB;
import com.easyframework.core.exception.ActionException;
import com.easyframework.core.util.DateUtil;
import com.easyframework.core.util.StringEscapeEditor;

/**
 * @Title: BaseAction.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:02:34
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:02:34
 */
public class BaseAction {
	protected final Logger logger = Logger.getLogger(BaseAction.class);
	protected final String REDIRECT = "redirect:";
	protected final String FROWARD = "forward:";

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true,
				false));
	}

	/**
	 * 该方法提供给子类调用，实现文件上传
	 * 
	 */
	/*protected String upload(HttpServletRequest request) {

		
		 * String basePath = Action.getRealPath("/WEB-INF/upload_files/"); //
		 * String subPath = sdf.format(new Date());
		 * 
		 * 
		 * File dir = new File(basePath + subPath); if (!dir.exists()) {
		 * dir.mkdirs(); }
		 * 
		 * String path = basePath + subPath + UUID.randomUUID().toString(); //
		 * File destFile = new File(path);
		 * 
		 * 
		 * upload.renameTo(destFile);
		 * 
		 * return path;
		 
		return null;

	}*/

	//TODO  未完成
	public String[] upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		session.setAttribute("progressBar", 0); // 定义指定上传进度的Session变量
		String error = "";
		int maxSize = 50 * 1024 * 1024; // 单个上传文件大小的上限
		DiskFileItemFactory factory = new DiskFileItemFactory();   // 基于磁盘文件项目创建一个工厂对象
		ServletFileUpload upload = new ServletFileUpload(factory); // 创建一个新的文件上传对象
		try {
			List items = upload.parseRequest(request);// 解析上传请求
			Iterator itr = items.iterator();// 枚举方法
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next(); // 获取FileItem对象
				if (!item.isFormField()) {// 判断是否为文件域
					if (item.getName() != null && !item.getName().equals("")) {// 判断是否选择了文件
						long upFileSize = item.getSize(); // 上传文件的大小
						String fileName = item.getName(); // 获取文件名
						// System.out.println("上传文件的大小:" + item.getSize());
						if (upFileSize > maxSize) {
							error = "您上传的文件太大，请选择不超过50M的文件";
							break;
						}
						// 此时文件暂存在服务器的内存中
						File tempFile = new File(fileName);// 构造临时对象
						String uploadPath = request.getSession().getServletContext().getRealPath("/WEB-INF/attached/file");
						File file = new File(uploadPath, tempFile.getName()); // 获取根目录对应的真实物理路径
						InputStream is = item.getInputStream();
						int buffer = 1024; // 定义缓冲区的大小
						int length = 0;
						byte[] b = new byte[buffer];
						double percent = 0;
						FileOutputStream fos = new FileOutputStream(file);
						while ((length = is.read(b)) != -1) {
							percent += length / (double) upFileSize * 100D; // 计算上传文件的百分比
							fos.write(b, 0, length); // 向文件输出流写读取的数据
							session.setAttribute("progressBar",
									Math.round(percent)); // 将上传百分比保存到Session中
						}
						fos.close();
						Thread.sleep(1000); // 线程休眠1秒
					} else {
						error = "没有选择上传文件！";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			error = "上传文件出现错误：" + e.getMessage();
		}
		if (!"".equals(error)) {
			request.setAttribute("error", error);
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("result", "文件上传成功！");
			request.getRequestDispatcher("upFile_deal.jsp").forward(request,
					response);
		}
		
		return null;
	}

	protected void export(HttpServletResponse response,
			FileExportCB fileExportCB, String exportFileName) {
		if (fileExportCB == null) {
			throw new ActionException("fileExportCB 不能为空");
		}
		try {
			if (exportFileName == null) {
				Date now = new Date();
				exportFileName = DateUtil.formateToYYMD(now) + "_"
						+ DateUtil.formateToHMS(now);
			} else {
				exportFileName = exportFileName.trim();
			}
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ java.net.URLEncoder.encode(exportFileName, "UTF-8"));
			// response.addHeader("Content-Length", "" + fileLength);
			response.setContentType("application/octet-stream");
			fileExportCB.run(response.getOutputStream());
			response.getOutputStream().flush();
			// toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 文件下载
	 * 
	 * @param response
	 * @param filePath
	 */
	protected void download(HttpServletResponse response, String filePath) {
		if (filePath == null || filePath.trim().equals("")) {
			throw new ActionException("filePath 不能为空");
		}
		try {
			// path是指欲下载的文件的路径。
			File file = new File(filePath);
			// 取得文件名。
			String filename = file.getName();
			// 取得文件的后缀名。
			String ext = filename.substring(filename.lastIndexOf(".") + 1)
					.toUpperCase();

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(
					filePath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			// toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 用户跳转JSP页面
	 * 
	 * 此方法不考虑权限控制
	 * 
	 * @param folder
	 *            路径
	 * @param jspName
	 *            JSP名称(不加后缀)
	 * @return 指定JSP页面
	 */
	/*
	 * @RequestMapping("/{folder}/{jspName}") public String
	 * redirectJsp(@PathVariable String folder, @PathVariable String jspName) {
	 * return "/" + folder + "/" + jspName; }
	 */

}
