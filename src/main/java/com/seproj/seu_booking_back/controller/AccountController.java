package com.seproj.seu_booking_back.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seproj.seu_booking_back.entity.UserAccount;
import com.seproj.seu_booking_back.service.IUserAccountService;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.core.http.HttpClient;
import com.aliyun.core.http.ProxyOptions;
import com.aliyun.httpcomponent.httpclient.ApacheAsyncHttpClientBuilder;
import com.aliyun.sdk.service.dysmsapi20170525.models.*;
import com.aliyun.sdk.service.dysmsapi20170525.*;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.KeyManager;
import javax.net.ssl.X509TrustManager;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("/")
public class AccountController {
    private IUserAccountService iUserAccountService;

    public AccountController(IUserAccountService iUserAccountService) {
        this.iUserAccountService = iUserAccountService;
    }

    @PostMapping("/account")
    public String Login(@RequestBody Map data) throws Exception {
        SecureRandom randomGen = new SecureRandom();
        String code = String.format("%04d", randomGen.nextInt(10000));
        String loginVal = data.get("loginVal").toString();
        String loginType = data.get("loginType").toString();
        if(Objects.equals(loginType, "Phone")) {
            // java.time.LocalDateTime time = null;
            try{
                QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
                queryWrapper.select("ID").eq("phoneNum", loginVal);
                Map<String, Object> result = iUserAccountService.getMap(queryWrapper);
                if(result == null) {
                    System.out.println("New Account Created By Phone");
                    UserAccount userAccount = new UserAccount();
                    userAccount.setUserName("User");
                    userAccount.setRealName("User");
                    userAccount.setPhoneNum(loginVal);
                    userAccount.setEmailAddr("No Email Address");
                    userAccount.setBirthDate("0000-00-00");
                    iUserAccountService.save(userAccount);
                }
            } catch(Exception e) {}
            LoginPhone(loginVal, code);
        } else if (Objects.equals(loginType, "Mail")) {
            try{
                QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
                queryWrapper.select("ID").eq("emailAddr", loginVal);
                Map<String, Object> result = iUserAccountService.getMap(queryWrapper);
                if(result == null) {
                    System.out.println("New Account Created By E-Mail");
                    UserAccount userAccount = new UserAccount();
                    userAccount.setUserName("User");
                    userAccount.setRealName("User");
                    userAccount.setPhoneNum("No Phone Number");
                    userAccount.setEmailAddr(loginVal);
                    userAccount.setBirthDate("1990-01-01");
                    iUserAccountService.save(userAccount);
                }
            } catch(Exception e) {}
            LoginMail(loginVal, code);
        }
        System.out.println(code);
        System.out.print(loginVal + ", " + loginType + '\n');
        return code;
    }

    public Boolean LoginPhone(String phoneNo, String code) throws ExecutionException, InterruptedException {
        // HttpClient Configuration
        HttpClient httpClient = new ApacheAsyncHttpClientBuilder()
                .connectionTimeout(Duration.ofSeconds(10)) // Set the connection timeout time, the default is 10 seconds
                .responseTimeout(Duration.ofSeconds(10)) // Set the response timeout time, the default is 20 seconds
                .maxConnections(128) // Set the connection pool size
                .maxIdleTimeOut(Duration.ofSeconds(50)) // Set the connection pool timeout, the default is 30 seconds
                // Configure the proxy
                .proxy(new ProxyOptions(ProxyOptions.Type.HTTP, new InetSocketAddress("<your-proxy-hostname>", 9001))
                        .setCredentials("<your-proxy-username>", "<your-proxy-password>"))
                // If it is an https connection, you need to configure the certificate, or ignore the certificate(.ignoreSSL(true))
                .x509TrustManagers(new X509TrustManager[]{})
                .keyManagers(new KeyManager[]{})
                .ignoreSSL(false)
                .build();

        // Configure Credentials authentication information, including ak, secret, token
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId("LTAI5tFEgYToujjR4b4EAAt2")
                .accessKeySecret("MIPXZ4lacj3Y020q1Hw2xuYB9BAiRo")
                //.securityToken("<your-token>") // use STS token
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .region("cn-hangzhou") // Region ID
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                        //.setConnectTimeout(Duration.ofSeconds(30))
                )
                .build();

        // Parameter settings for API request
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(phoneNo)
                .signName("seubooking")
                .templateCode("SMS_460816095")
                .templateParam("{\"code\":\""+code+"\"}")
                // Request-level configuration rewrite, can set Http request parameters, etc.
                // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                .build();

        // Asynchronously get the return value of the API request
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        // Synchronously get the return value of the API request
        SendSmsResponse resp = response.get();
        if(resp == null) {
            System.out.println("Test If Empty");
        }
        System.out.println(new Gson().toJson(resp));
        // Asynchronous processing of return values
        /*response.thenAccept(resp -> {
            System.out.println(new Gson().toJson(resp));
        }).exceptionally(throwable -> { // Handling exceptions
            System.out.println(throwable.getMessage());
            return null;
        });*/
        //System.out.println("Test If Reach");
        // Finally, close the client
        client.close();
        return true;
    }

    public Boolean LoginMail(String addr, String code) throws GeneralSecurityException {
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.port", "465");
        // 发件人的账号，填写控制台配置的发信地址,比如xxx@xxx.com
        props.put("mail.user", "617661369@qq.com");
        // 访问SMTP服务时需要提供的密码(在控制台选择发信地址进行设置)
        props.put("mail.password", "swzgzczlysyabdbj");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
//        mailSession.setDebug(true);
        //UUID uuid = UUID.randomUUID();
        //final String messageIDValue = "<" + uuid.toString() + ">";
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession) {
            //@Override
            //protected void updateMessageID() throws MessagingException {
            //设置自定义Message-ID值
            //setHeader("Message-ID", messageIDValue);
            //}
        };
        try {
            // 设置发件人邮件地址和名称。填写控制台配置的发信地址,比如xxx@xxx.com。和上面的mail.user保持一致。名称用户可以自定义填写。
            InternetAddress from = new InternetAddress("617661369@qq.com", "617661369");
            message.setFrom(from);

            // 设置收件人邮件地址，比如yyy@yyy.com
            InternetAddress to = new InternetAddress(addr);
            message.setRecipient(MimeMessage.RecipientType.TO, to);

            // 设置邮件标题
            message.setSubject("SEU Booking 登录验证码");
            message.setHeader("Content-Transfer-Encoding", "base64");
            // 设置邮件的内容体 type: text/plain（纯文本）text/html（HTML 文档）
            message.setContent("<!DOCTYPE html>\n<html>\n<head>\n<meta charset=\"utf-8\">\n<title>hello world</title>\n</head>\n<body>\n " +
                    "<h1>SEU Booking 登录：</h1>\n    <p>您的验证码是：" + code + ", 该验证码五分钟内有效，请勿告知他人。</p>\n</body>\n</html>", "text/html;charset=UTF-8");
            //发送邮件
            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            String err = e.getMessage();
            err = new String(err.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            System.out.println(err);
        }
        return true;
    }
}
