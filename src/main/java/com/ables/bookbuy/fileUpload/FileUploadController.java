package com.ables.bookbuy.fileUpload;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ables.bookbuy.models.User;
import com.ables.bookbuy.service.UserService;
import com.ables.bookbuy.util.FileUtils;

import java.io.IOException;
import java.time.Instant;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("user")
public class FileUploadController {

    private final StorageService storageService;
    @Autowired
    private final  UserService userService;
    
    @Autowired
    StorageProperties prop;
    @Autowired
    private FileUtils ft;
    private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    public FileUploadController(StorageService storageService, UserService userService) {
        this.storageService = storageService;
        this.userService = userService;
    }

    @GetMapping("/getupload")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @PostMapping("/profile_up")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,@ModelAttribute("user") User user) {
//    	String fileName = Integer.toString(new Random(1000000000).nextInt());
    	long instant = Instant.now().toEpochMilli();
    	String ext = ft.getFileExtension(file.getOriginalFilename());
    	String fileName = String.valueOf(instant)+"."+ext;
    	
    	logger.info("saved filename is "+fileName);
        storageService.store(file,fileName);
        
        user.setProfilePic(prop.getLocation()+"/"+ fileName);
        logger.info(user.getProfilePic()+"random number ");
        userService.saveOrUpdate(user);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
