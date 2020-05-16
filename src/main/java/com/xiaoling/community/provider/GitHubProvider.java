package com.xiaoling.community.provider;

import com.alibaba.fastjson.JSON;
import com.xiaoling.community.controller.AuthorizeController;
import com.xiaoling.community.dto.AccessonTokenDto;
import com.xiaoling.community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {
    //拿到accesstaken
    public String getAccessTaken(AccessonTokenDto accessonTokenDto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessonTokenDto));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string =response.body().string();
                String token =string.split("&")[0].split("=")[1];
                //System.out.println(token);
                return token ;
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
    }
    //拿到user
    public GitHubUser getUser(String acessToken) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+acessToken)
                .build();
        Response response = client.newCall(request).execute();

        try {
            String string = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }




    }
}
