package com.ables.bookbuy.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

@WebServlet(urlPatterns={"/image"})
public class ImageServlet extends  HttpServlet{
	Logger logger = LoggerFactory.getLogger(ImageServlet.class);
	ServletOutputStream out;
	FileInputStream inps;
	BufferedInputStream bin;
	BufferedOutputStream bout;
	String profile_pic = "/home/ables/upload-dir/images.png";
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res){
	
	res.setContentType("image/jpg");
	String pin = req.getParameter("pin");
	logger.info("file location "+pin);
	
	try {
		
		 out = res.getOutputStream();
		 
		 if(pin != null && pin != ""){
			 logger.info("going with pin");
			 inps = new FileInputStream(pin);
		 }
		 else{
			 logger.info("profile pic from "+profile_pic);
			 inps = new FileInputStream(profile_pic);
		 }
		 bin = new BufferedInputStream(inps);
		 bout = new BufferedOutputStream(out);
		
		int c =0;
		while((c = bin.read()) != -1){
			bout.write(c);
		}
		 
	}
		catch(FileNotFoundException fex){
			logger.error("profile pic not found");
		}
		catch(IOException ioex){
		logger.error("IOException thrown");	
		}
	 finally {
		
		try {
			bin.close();
			out.close();
			inps.close();
			bout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("error closing streaming");
		}
	}
	
	
}
}