package org.wecancodeit.securitydemo.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.wecancodeit.securitydemo.post.ImageUploadService;
import org.wecancodeit.securitydemo.post.Post;
import org.wecancodeit.securitydemo.post.PostRepository;

@Controller
public class SiteController {

	@Resource
	private PostRepository postRepo;

	@Resource
	ImageUploadService uploader;

	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "auth/login";
	}

	@RequestMapping("/posts")
	public String seePosts(Model model) {
		model.addAttribute("postsModel", postRepo.findAll());
		return "layouts/posts";
	}
	
		
	@RequestMapping("/admin/testing")
	public String secure() {
	    return "layouts/admin-only";
	  }
	

	@RequestMapping("/admin/posts") // only accessible from admin side (try hitting on this endpoint as a
									// guest and access will be forbidden)
	public String addPost(Model model) {
		model.addAttribute("postsModel", postRepo.findAll());
		return "layouts/post";
	}

	@PostMapping("/admin/addPost")
	public String addPostWithImage(@RequestParam(value = "postContent", required = true) String content,
			@RequestParam("imageFile") MultipartFile imageFile) throws Exception {

		String virtualFileUrl = uploader.uploadMultipartFile(imageFile);
		System.out.println(content);
		postRepo.save(new Post(content, "/uploads/" + virtualFileUrl));

		return "redirect:/posts";
	}

	@GetMapping("/uploads/{file:.+}") // allows "." to be part of PathVariable instead of truncating it
	public void serveImage( HttpServletResponse response,
			@PathVariable("file") String fileName) throws Exception {

		// Resolve path of requested file
		File requestedFile = uploader.getUploadedFile(fileName);


		// Serve file by streaming it directly to the response
		InputStream in = new FileInputStream(requestedFile);
		IOUtils.copy(in, response.getOutputStream());
	}

}