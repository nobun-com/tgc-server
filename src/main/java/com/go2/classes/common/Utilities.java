package com.go2.classes.common;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class Utilities {

    public final static SimpleDateFormat dateWithoutTime = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat datePicker = new SimpleDateFormat("MM/dd/yyyy");
    public final static SimpleDateFormat dayOnly = new SimpleDateFormat("EEE");
    public final static SimpleDateFormat timeOnly = new SimpleDateFormat("hh:mm a");

    public static String saveImage(MultipartFile receivedFile) {
	String uuid = UUID.randomUUID().toString();
	File file;
	try {
	    file = File.createTempFile(uuid, "." + FilenameUtils.getExtension(receivedFile.getOriginalFilename()));
	    FileOutputStream fos = new FileOutputStream(file);
	    fos.write(receivedFile.getBytes());
	    fos.close();
	} catch (Exception e) {
	    throw new IllegalStateException("unable to upload file");
	}

	AWSCredentials credentials = new BasicAWSCredentials("AKIAIXLL5ECNROY3II6A", "DyWui3ktmF16iqKiLqgsIjKn/D3UJnD2726qwP4d");
	AmazonS3 s3client = new AmazonS3Client(credentials);
	s3client.putObject(new PutObjectRequest("tgc-img-s3-dev-1", file.getName(), file).withCannedAcl(CannedAccessControlList.PublicRead));
	URL url = s3client.getUrl("tgc-img-s3-dev-1", file.getName());
	return url.toExternalForm();
    }

}
