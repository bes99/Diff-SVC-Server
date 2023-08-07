package com.example.diffsvcserver.voice;

import com.example.diffsvcserver.error.InvalidInputException;
import com.example.diffsvcserver.error.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelVoiceService {
    private final ModelVoiceRepository modelVoiceRepository;
    public ModelVoice findById(Long id){
        ModelVoice modelVoice = modelVoiceRepository.findById(id).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_MODELVOICE_ID));
        return modelVoice;
    }
}
