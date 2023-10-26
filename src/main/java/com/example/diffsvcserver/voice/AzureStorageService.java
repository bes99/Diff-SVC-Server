package com.example.diffsvcserver.voice;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class AzureStorageService {
    private final BlobServiceClient blobServiceClient;
    @Value("${azure.blob.file-name}")
    private String jsonFileName;
    @Value("${azure.storage.container-name}")
    private String containerName;
    public String uploadFileToBlob(MultipartFile file) throws Exception {
        try (InputStream inputStream = file.getInputStream()) {
            BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(file.getOriginalFilename());
            blobClient.upload(inputStream, file.getSize());
            return blobClient.getBlobUrl();
        }
    }
    @PostConstruct
    public void init() {
        downloadServiceAccountKey(containerName);
    }
    public void downloadServiceAccountKey(String containerName) {
        BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(jsonFileName);
        Path downloadPath = Paths.get(jsonFileName);
        if (!Files.exists(downloadPath)) {
            blobClient.downloadToFile(downloadPath.toString());
        }
    }
}
