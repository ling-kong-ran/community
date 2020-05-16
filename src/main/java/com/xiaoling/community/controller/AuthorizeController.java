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

import javax.servlet.http.HttpServletRequest;
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
                            @Autowired HttpServletRequest request) throws IOException {

        AccessonTokenDto accessonTokenDto = new AccessonTokenDto();
        accessonTokenDto.setCode(code);
        accessonTokenDto.setClient_secret(secret);
        accessonTokenDto.setRedirect_uri(redirectUrl);
        accessonTokenDto.setClient_id(ClientID);
        accessonTokenDto.setState(state);
        String acessToken=gitHubProvider.getAccessTaken(accessonTokenDto);//post
        GitHubUser gitHubUser=gitHubProvider.getUser(acessToken);
        System.out.println(gitHubUser.getName());
        if(gitHubUser != null){
            //登录成功，写cookie和session
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(gitHubUser.getName());
            user.setAccountID(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            request.getSession().setAttribute("gitHubUser",gitHubUser);
            return "redirect:/";

        }else{
            //重新登录
            return "redirect:/";
        }


    }
}
