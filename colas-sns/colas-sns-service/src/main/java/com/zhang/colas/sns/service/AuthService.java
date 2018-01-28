package com.zhang.colas.sns.service;

import com.zhang.colas.common.vo.GithubUser;
import com.zhang.colas.sns.entity.SUser;

public interface AuthService {

    /**
     * 获取用户
     * @param githubId
     * @return
     */
    SUser getSUserByGithubId(Integer githubId);

    /**
     * 使用github信息创建新用户
     * @param githubUser
     * @return
     */
    SUser createUser(GithubUser githubUser);

    SUser getUserById(int id);
}
