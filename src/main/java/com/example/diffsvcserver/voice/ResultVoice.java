package com.example.diffsvcserver.voice;

import com.example.diffsvcserver.user.User;

import javax.persistence.*;

@Entity
@Table(name = "result_voice_tb")
public class ResultVoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "result_voice")
    private String resultVoice;
    @Column(name = "select_model")
    private String selectModel;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
