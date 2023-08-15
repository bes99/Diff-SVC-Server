package com.example.diffsvcserver.voice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseModelVoice {
    private Long id;
    private String image;
    private String name;
    private String description;

}
