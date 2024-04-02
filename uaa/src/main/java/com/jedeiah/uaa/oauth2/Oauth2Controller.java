package com.jedeiah.uaa.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jedeiah.commons.vo.RespVo;
import com.jedeiah.uaa.config.Oauth2Properties;
import com.jedeiah.uaa.service.UsersService;
import com.jedeiah.uaa.vo.GitHubUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

/**
 * @author chj
 * @since 2024-03-30
 */
@RestController
@RequestMapping("/oauth2")
@Tag(name = "oauth2授权")
@Slf4j
public class Oauth2Controller {

    @Autowired
    private Oauth2Properties oauth2Properties;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsersService usersService;


    /**
     * 让用户跳转到 GitHub
     * 这里不能加@ResponseBody，因为这里是要跳转而不是返回响应
     *
     * @return 跳转url
     */
    @GetMapping("/authorizeUrl")
    @Operation(summary = "让用户跳转到 GitHub")
    public RespVo<String> authorize() {
        String url = oauth2Properties.getAuthorizeUrl() +
                "?client_id=" + oauth2Properties.getClientId() +
                "&redirect_uri=" + oauth2Properties.getRedirectUrl();
        log.info("授权url:{}", url);
        return RespVo.success(url);
    }


    /**
     * 回调接口，用户同意授权后，GitHub会重定向到此路径
     *
     * @param code GitHub重定向时附加的授权码，只能用一次
     * @return
     */
    @GetMapping("/callback/github/authorizationJwt/token")
    public RespVo callback(@RequestParam("code") String code) {
        log.info("code={}", code);
        String accessToken = getAccessToken(code);
        GitHubUser userInfo = getUserInfo(accessToken);
        //授权
        String authorizationJwt = usersService.loginOrRegister(userInfo);
        return RespVo.success(authorizationJwt);
    }

    private String getAccessToken(String code) {
        String url = oauth2Properties.getAccessTokenUrl() +
                "?client_id=" + oauth2Properties.getClientId() +
                "&client_secret=" + oauth2Properties.getClientSecret() +
                "&code=" + code +
                "&grant_type=authorization_code";
        log.info("getAccessToken url:{}", url);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseStr = response.body();
            log.info("responseStr={}", responseStr);
            objectMapper.writeValueAsString(responseStr);
            // 将JSON字符串转换为Map对象
            Map<String, Object> map = objectMapper.readValue(responseStr, Map.class);
            String accessToken = (String) map.get("access_token");
            log.info("accessToken={}", accessToken);
            return accessToken;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private GitHubUser getUserInfo(String accessToken) {
        String url = oauth2Properties.getUserInfoUrl();
        log.info("getUserInfo url:{}", url);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Authorization", "token " + accessToken)
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseStr = response.body();
            log.info("responseStr={}", responseStr);
            objectMapper.writeValueAsString(responseStr);
            // 将JSON字符串转换为Map对象
            GitHubUser gitHubUser = objectMapper.readValue(responseStr, GitHubUser.class);
            log.info("userInfo={}", gitHubUser);
            return gitHubUser;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
