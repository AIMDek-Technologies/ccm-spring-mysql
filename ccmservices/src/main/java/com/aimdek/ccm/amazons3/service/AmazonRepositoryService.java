/**
 * 
 */
package com.aimdek.ccm.amazons3.service;

import static com.aimdek.ccm.util.CCMConstant.AMAZON_PRESIGNED_URL_EXPIRATION_INTERVAL;

import java.io.File;
import java.net.URL;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aimdek.ccm.util.CommonUtil;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * The Class AmazonRepositoryService.
 *
 * @author aimdek.team
 */
@Service("amazonRepositoryService")
public class AmazonRepositoryService {

	/** The secret key. */
	private String secretKey;

	/** The access key. */
	private String accessKey;

	/** The bucket name. */
	private String bucketName;

	/** The s3 client. */
	private AmazonS3 amazonS3Client;

	/**
	 * Sets the amazon s3 client.
	 *
	 * @param amazonS3Client
	 *            the amazonS3Client to set
	 */
	public void setAmazonS3Client(AmazonS3 amazonS3Client) {
		this.amazonS3Client = amazonS3Client;
	}

	/**
	 * Gets the s3 client.
	 *
	 * @return the s3Client
	 */
	public AmazonS3 getAmazonS3Client() {
		if (CommonUtil.isNull(amazonS3Client)) {
			amazonS3Client = createAmazonS3Client();
		}
		return amazonS3Client;
	}

	/**
	 * Instantiates a new amazon repository service.
	 */
	public AmazonRepositoryService() {

	}

	/**
	 * Instantiates a new amazon repository service.
	 *
	 * @param secretKey
	 *            the secret key
	 * @param accessKey
	 *            the access key
	 * @param bucketName
	 *            the bucket name
	 */
	public AmazonRepositoryService(@Value("${secretKey}") String secretKey, @Value("${accessKey}") String accessKey, @Value("${bucketName}") String bucketName) {
		this.secretKey = secretKey;
		this.accessKey = accessKey;
		this.bucketName = bucketName;
	}

	/**
	 * Upload file to aws s3 server.
	 *
	 * @param file
	 *            the file
	 * @param filePath
	 *            the file path
	 */
	public void uploadFileToAmazonS3Server(File file, String filePath) {

		if (CommonUtil.isNotNull(file) && CommonUtil.isNotNull(filePath)) {
			AmazonS3 amazonS3 = getAmazonS3Client();
			// Put object
			amazonS3.putObject(new PutObjectRequest(this.bucketName, filePath, file));

		}
	}

	/**
	 * generrateSignedUrl AmazonRepositoryService.
	 *
	 * @param filePath
	 *            the file path
	 * @return String
	 */
	public String generateSignedUrl(String filePath) {
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, filePath);
		generatePresignedUrlRequest.setMethod(HttpMethod.GET);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(CommonUtil.toDay());
		calendar.add(Calendar.YEAR, AMAZON_PRESIGNED_URL_EXPIRATION_INTERVAL);
		generatePresignedUrlRequest.setExpiration(calendar.getTime());

		URL url = getAmazonS3Client().generatePresignedUrl(generatePresignedUrlRequest);
		return url.toString();
	}

	/**
	 * Creates the amazon s3 client.
	 *
	 * @return the amazon s3
	 */
	private AmazonS3 createAmazonS3Client() {

		if (CommonUtil.isNull(amazonS3Client)) {

			amazonS3Client = new AmazonS3Client(new AWSCredentials() {

				@Override
				public String getAWSSecretKey() {
					return secretKey;
				}

				@Override
				public String getAWSAccessKeyId() {
					return accessKey;
				}
			});
		}

		return amazonS3Client;
	}

	/**
	 * Delete Uploaded Profile Picture.
	 *
	 * @param filePath
	 *            the file path
	 */
	public void deleteFile(String filePath) {
		DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, filePath);
		getAmazonS3Client().deleteObject(deleteObjectRequest);
	}

}
