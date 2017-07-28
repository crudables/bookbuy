package com.ables.bookbuy.controller;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.ables.bookbuy.forms.RegisterForm;
//import com.ables.bookbuy.auth.service.SecurityService;
//import com.ables.bookbuy.auth.service.validator.UserValidator;
import com.ables.bookbuy.models.User;
import com.ables.bookbuy.service.UserService;

@SessionAttributes(value="user")
@Controller
public class UserController implements ServletContextAware {
	@Autowired
	private UserService service;
	@Autowired
	private com.ables.bookbuy.util.FileUtils fut;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static String UPLOAD_FOLDER = "";
	private ServletContext appContext;
	private String loc = null;
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegisterForm(Model model,RegisterForm registerForm){
		System.out.println("Showing reg form");
//		model.addAttribute("user", new User());
		logger.info("showing register form");
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register( RegisterForm registerForm, Model model, BindingResult bind){

	        if (bind.hasErrors()) {
	        	logger.error(bind.toString());
	        	logger.info("There was an error");
	        	model.addAttribute("registerForm", new RegisterForm());
	            return "register";
	        }
	        User user = new User();
	        user.setUsername(registerForm.getUsername());
	        user.setFirstName(registerForm.getFirstName());
	        user.setLastName(registerForm.getLastName());
	        user.setEmail(registerForm.getEmail());
	        user.setPassword(registerForm.getPassword());
	        user.setEnabled(true);
	        service.saveOrUpdate(user);


	        return "registered";
	}
	
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	 public String user(){
		logger.info(appContext.getContextPath()+" is the servlet context");
		 return "user";
	 }
	
	@GetMapping("/upload")
	public String getUpload(){
		return "upload";
	}
	
	@PostMapping("/profile_up")
	public String postUpload(MultipartFile file, @ModelAttribute("user") User user){
		logger.info("Got upload request");
		try {
			saveImage(file,user.getUsername());
			user.setProfilePic(loc);
			service.saveOrUpdate(user);
			logger.info("profile ppic set to "+loc);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/";
	}

	@Override
	public void setServletContext(ServletContext context) {
	appContext = 	context;
	}

	private void saveImage(MultipartFile image,String username)
			throws RuntimeException, IOException {
			try {
			String	userDir = System.getProperty("user.home");
			loc = userDir+"/upload-dir/"+username+"/"
					+ String.valueOf(Instant.now().getEpochSecond()+"."+fut.getFileExtension(image.getOriginalFilename()));
			File file = new File(loc);
			 
			FileUtils.writeByteArrayToFile(file, image.getBytes());
			System.out.println("Go to the location:  " + loc
			+ " on your computer and verify that the image has been stored.");
			} catch (IOException e) {
			throw e;
			}
			}
}
