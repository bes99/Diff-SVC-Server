package com.example.diffsvcserver.voice;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class AzureStorageService {
    private final BlobServiceClient blobServiceClient;
    public String uploadFileToBlob(MultipartFile file, String containerName) throws Exception {
        try (InputStream inputStream = file.getInputStream()) {
            BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(file.getOriginalFilename());
            blobClient.upload(inputStream, file.getSize());
            return blobClient.getBlobUrl();
        }
    }
}
