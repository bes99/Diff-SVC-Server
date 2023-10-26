package com.example.diffsvcserver.tts;

import com.google.cloud.texttospeech.v1.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class TTSService {
    @Value("${azure.blob.file-name}")
    private String jsonFileName;
    private TextToSpeechClient initializeTtsClient() throws Exception {
        Path path = Paths.get(jsonFileName);
        log.info("path :" + path.toAbsolutePath());
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", path.toAbsolutePath().toString());

        String credentialsPath = System.getProperty("GOOGLE_APPLICATION_CREDENTIALS");
        log.info("Set GOOGLE_APPLICATION_CREDENTIALS: " + credentialsPath);

        if (credentialsPath == null || credentialsPath.isEmpty()) {
            throw new Exception("Failed to set GOOGLE_APPLICATION_CREDENTIALS");
        }

        return TextToSpeechClient.create();
    }

    /**
     * https://cloud.google.com/text-to-speech/docs/create-audio?hl=ko
     */

    public byte[] convertTextToSpeech(String text) throws Exception {
        try (TextToSpeechClient textToSpeechClient = initializeTtsClient()) {
            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("ko-KR")
                    .setSsmlGender(SsmlVoiceGender.FEMALE)
                    .build();
            AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();

            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
            return response.getAudioContent().toByteArray();
        }
    }
}
