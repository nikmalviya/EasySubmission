package com.project.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class S3Client {
    private AmazonS3 amazonS3;

    @Value("${s3.endpointurl}")
    private String endPoint;
    @Value("${s3.bucketName}")
    private String bucketName;
    @Value("${s3.accesskey}")
    private String accessKey;
    @Value("${s3.secretekey}")
    private String secretKey;

    private Regions region;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    private String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public AmazonS3 getAmazonS3() {
        return amazonS3;
    }

    public void setAmazonS3(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
    @PostConstruct
    private void initializeCredentials()
    {
        this.region=Regions.US_EAST_1;
        AWSCredentials awsCredentials=new BasicAWSCredentials(this.getAccessKey(),this.getSecretKey());
        this.amazonS3=new AmazonS3Client(awsCredentials);
    }

    @Override
    public String toString() {
        return "S3Client{" +
                "amazonS3=" + amazonS3 +
                ", endPoint='" + endPoint + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", region=" + region +
                '}';
    }
}
