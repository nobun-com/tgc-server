package com.go2.classes.common;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.go2.classes.models.jpa.FilesEntity;

import org.apache.commons.io.FilenameUtils;

public class Utilities {

	public final static SimpleDateFormat dateWithoutTime = new SimpleDateFormat("yyyy-MM-dd");
	public final static SimpleDateFormat datePicker = new SimpleDateFormat("MM/dd/yyyy");
	public final static SimpleDateFormat dayOnly = new SimpleDateFormat("EEE");
	public final static SimpleDateFormat timeOnly = new SimpleDateFormat("hh:mm a");
	
	public static FilesEntity saveImage(MultipartFile receivedFile, String filePath) {
		String uuid = UUID.randomUUID().toString();
		filePath = filePath + "\\" + uuid + "." + FilenameUtils.getExtension(receivedFile.getOriginalFilename());
		File file = new File(filePath);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(receivedFile.getBytes());
			fos.close();
		} catch (Exception e) {
			throw new IllegalStateException("unable to upload file");
		}
		return new FilesEntity(uuid, filePath);
	}

	public static File getImage(String filePath) {
		File file = new File(filePath);
		return file;
	}

}
