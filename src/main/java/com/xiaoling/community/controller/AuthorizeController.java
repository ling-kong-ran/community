package com.xiaoling.community.controller;

import com.xiaoling.community.dto.AccessonTokenDto;
import com.xiaoling.community.dto.GitHubUser;
import com.xiaoling.community.mapper.UserMapper;
import com.xiaoling.community.model.User;
import com.xiaoling.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String ClientID;
    @Value("${github.client.secret}")
    private  String secret;
    @Value("${github.redirect.uri}")
    private String redirectUrl;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "state") String state,

            HttpServletResponse response) throws IOException {


        AccessonTokenDto accessonTokenDto = new AccessonTokenDto();
        accessonTokenDto.setCode(code);
        accessonTokenDto.setClient_secret(secret);
        accessonTokenDto.setRedirect_uri(redirectUrl);
        accessonTokenDto.setClient_id(ClientID);
        accessonTokenDto.setState(state);
        String acessToken=gitHubProvider.getAccessTaken(accessonTokenDto);//post
        GitHubUser gitHubUser=new GitHubUser();
                gitHubUser=gitHubProvider.getUser(acessToken);

        if(gitHubUser != null){
            //登录成功，写cookie和session
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountKey(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(gitHubUser.getAvatar_url());
            user.setBio(gitHubUser.getBio());

            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";

        }else{
            //重新登录
            return "redirect:/";
        }


    }
}
