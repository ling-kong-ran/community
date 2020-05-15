package com.xiaoling.community.controller;

import com.xiaoling.community.dto.AccessonTokenDto;
import com.xiaoling.community.dto.GitHubUser;
import com.xiaoling.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

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

    @GetMapping("/callback")
    public String callback(
                            @RequestParam(name = "code") String code,
                            @RequestParam(name = "state") String state) throws IOException {
        AccessonTokenDto accessonTokenDto = new AccessonTokenDto();
        accessonTokenDto.setCode(code);
        accessonTokenDto.setClient_secret(secret);
        accessonTokenDto.setRedirect_uri(redirectUrl);
        accessonTokenDto.setClient_id(ClientID);
        accessonTokenDto.setState(state);
        String acessToken=gitHubProvider.getAccessTaken(accessonTokenDto);//post
        GitHubUser user=gitHubProvider.getUser(acessToken);
        System.out.println(user.getName());
        return "index";
    }
}
