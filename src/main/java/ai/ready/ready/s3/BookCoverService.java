package ai.ready.ready.s3;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BookCoverService {

    private static final Logger logger = LoggerFactory.getLogger(BookCoverService.class);
    private final S3Client s3Client;
    private final String bucket = "bookcovers-luminar";

    public void uploadCover(MultipartFile file, String bookIsbn) {
        logger.info("Uploading cover of book with isbn: {}", bookIsbn);
        try {
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(bookIsbn)
                            .build(),
                    software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
            logger.info("Cover of: {} uploaded successfully", bookIsbn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCover(String isbn) {
        logger.info("Deleting Cover of: {}", isbn);
        s3Client.deleteObject(DeleteObjectRequest.builder()
                        .bucket(bucket)
                        .key(isbn)
                .build());
        logger.info("Cover of deleted successfully: {}", isbn);
    }
}
