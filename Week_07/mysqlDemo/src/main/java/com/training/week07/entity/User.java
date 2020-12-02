package com.training.week07.entity;

import lombok.Data;

@Data
public class User implements java.io.Serializable {
    private static final long serialVersionUID = -3459552884357152821L;

    private Long userId;
    private String nickname;
    private String account;
    private Integer validity;
    private String remark;
    private String password;
    private String salt;
    private Long createTime;
    private Long changeTime;

    public User() {
        super();
    }

    public User(Long userId, String nickname, String account, Integer validity, String remark, String password,
            String salt, Long createTime, Long changeTime) {
        super();
        this.userId = userId;
        this.nickname = nickname;
        this.account = account;
        this.validity = validity;
        this.remark = remark;
        this.password = password;
        this.salt = salt;
        this.createTime = createTime;
        this.changeTime = changeTime;
    }
}