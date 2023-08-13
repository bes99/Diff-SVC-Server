package com.example.diffsvcserver.voice;

import com.example.diffsvcserver.error.InvalidInputException;
import com.example.diffsvcserver.error.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ModelVoiceService {
    private final ModelVoiceRepository modelVoiceRepository;
    private final AzureStorageService azureStorageService;
    @Value("${azure.storage.container-name}")
    private String containerName;
    public ModelVoice findById(Long id){
        ModelVoice modelVoice = modelVoiceRepository.findById(id).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_MODELVOICE_ID));
        return modelVoice;
    }

    @Transactional
    public void uploadAndSave(String name, String description, String tag,
                              MultipartFile file) throws Exception {
        String url = azureStorageService.uploadFileToBlob(file, containerName);
        ModelVoice modelVoice = ModelVoice.builder()
                .name(name)
                .description(description)
                .tag(tag)
                .url(url)
                .build();
        modelVoiceRepository.save(modelVoice);
    }
}
