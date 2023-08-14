package com.example.diffsvcserver.voice;

import com.example.diffsvcserver.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "model_voice_tb")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ModelVoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "model_name")
    private String name;
    private String description;
    private String tag;
    private String url;
    private String image;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
