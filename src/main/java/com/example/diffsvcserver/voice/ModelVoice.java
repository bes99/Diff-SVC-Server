package com.example.diffsvcserver.voice;

import com.example.diffsvcserver.favorite.Favorite;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "modelVoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites = new ArrayList<>();
}
