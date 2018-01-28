package com.zhang.colas.sns.service.impl;

import com.zhang.colas.common.vo.GithubUser;
import com.zhang.colas.sns.entity.SUser;
import com.zhang.colas.sns.entity.UserProfile;
import com.zhang.colas.sns.mapper.SUserMapper;
import com.zhang.colas.sns.mapper.UserProfileMapper;
import com.zhang.colas.sns.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SUserMapper userMapper;
    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public SUser getSUserByGithubId(Integer githubId) {
        List<SUser> users = userMapper.getUserByGithubId(githubId);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public SUser createUser(GithubUser githubUser) {
        SUser user = new SUser();
        user.setGithubId(githubUser.getId());
        user.setCreateTime(new Date());
        userMapper.insertSelective(user);

        UserProfile profile = new UserProfile();
        profile.setUserId(user.getId());
        profile.setAvatarUrl(githubUser.getAvatar_url());
        profile.setNickname(githubUser.getLogin());
        profile.setCreateBy(user.getId());
        profile.setCreateTime(new Date());

        userProfileMapper.insertSelective(profile);
        return user;
    }

    @Override
    public SUser getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
