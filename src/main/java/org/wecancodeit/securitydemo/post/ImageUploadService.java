package org.wecancodeit.securitydemo.post;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUploadService {

	String uploadDirectory = "C:\\Users\\WeCanCodeIT\\wcci\\code-flex\\spring2-security-demo\\src\\main\\resources\\static\\uploads";

	private String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public String uploadMultipartFile(MultipartFile imageFile) throws Exception {
		// Upload image - stream uploaded data to a temporary file
		String fileName = imageFile.getOriginalFilename();

		// Transfer the temporary file to its permanent location
		String fileExtension = getFileExtension(fileName);
		String virtualFileUrl = UUID.randomUUID().toString() + "." + fileExtension;
		File fileUpload = new File(uploadDirectory, virtualFileUrl);
		imageFile.transferTo(fileUpload);

		return virtualFileUrl;
	}

	public File getUploadedFile(String fileName) {
		Path filePath = Paths.get(uploadDirectory, fileName);
		String filePathString = filePath.toString();
		return new File(filePathString);
	}

}
