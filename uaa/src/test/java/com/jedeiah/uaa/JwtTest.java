package com.jedeiah.uaa;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class JwtTest {
    @Test
    public void testCreateJwt() {
        JwtBuilder builder = Jwts.builder()
                .setId("888")             //设置唯一编号
                .setSubject("啦啦啦")       //设置主题  可以是JSON数据
                .setIssuedAt(new Date())  //设置签发日期
                //参数1：签名算法，参数2：秘钥（盐）
                .signWith(SignatureAlgorithm.HS256, "maltose");//设置签名 使用HS256算法，并设置SecretKey(字符串)
        //获取密文 并返回一个字符串
        System.out.println(builder.compact());
    }
}
