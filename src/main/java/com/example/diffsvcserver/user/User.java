package com.example.diffsvcserver.user;

import com.example.diffsvcserver.voice.ResultVoice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "user_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_pwd")
    private String userPwd;
    private String email;
    private String sex;
    @Column(name = "select_model")
    private String selectModel;
    @Column(name = "user_image")
    private String image;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResultVoice> resultVoices;

}
