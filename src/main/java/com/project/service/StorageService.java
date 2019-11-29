package com.project.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.project.config.S3Client;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class StorageService {
    final private S3Client s3ClientService;

    public StorageService(S3Client s3ClientService) {
        this.s3ClientService = s3ClientService;
    }

    public File convertMultipartFile(MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile.getOriginalFilename());
        return File.createTempFile(multipartFile.getOriginalFilename(), ".tmp");
//        FileOutputStream fos = new FileOutputStream(file);
//        fos.write(multipartFile.getBytes());
//        fos.close();
//        return file;
    }
    //it is general method to delete assignments and submissions file from s3
//    public String deleteFile(String fileUrl) {
//        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
//        this.s3ClientService
//                .getAmazonS3()
//                .deleteObject(new DeleteObjectRequest(this.s3ClientService.getBucketName() + "/", fileName));
//        return "Successfully deleted";
//    }


    //this method will upload the file on s3 bucket and will return url path of file
    public String uploadAssignment(MultipartFile multipartfile) throws IOException {
        return uploadFile(multipartfile);
    }

    private String uploadFile(MultipartFile multipartfile) throws IOException {
        File file = this.convertMultipartFile(multipartfile);
        String bucketName = s3ClientService.getBucketName();
        String endPoint = s3ClientService.getEndPoint();
        String fileName = RandomString.make() + "-" + multipartfile.getOriginalFilename();
        String filePath = endPoint + "/" + fileName;
        s3ClientService.getAmazonS3().putObject(new PutObjectRequest(s3ClientService.getBucketName(), fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return filePath;
    }

    public String uploadSubmission(MultipartFile multipartfile) throws IOException {
        return uploadFile(multipartfile);
    }

}
