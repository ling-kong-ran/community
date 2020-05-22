package com.xiaoling.community.controller;

import com.xiaoling.community.dto.AccessonTokenDto;
import com.xiaoling.community.dto.GitHubUser;
import com.xiaoling.community.model.User;
import com.xiaoling.community.provider.GitHubProvider;
import com.xiaoling.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    private UserService userService;

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
        GitHubUser gitHubUser=gitHubProvider.getUser(acessToken);


        if(gitHubUser != null&&gitHubUser.getId() != null){
            //登录成功，写cookie和session
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountKey(String.valueOf(gitHubUser.getId()));
            user.setAvatarUrl(gitHubUser.getAvatar_url());
            userService.createOrUptaed(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";

        }else{
            //重新登录
            return "redirect:/";
        }


    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
       // model.addAttribute("life",cookie);
        return "redirect:/";
    }
}
