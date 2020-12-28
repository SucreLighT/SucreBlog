package cn.sucrelt.sucreblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.sucrelt.sucreblog.dao")
@SpringBootApplication
public class SucreBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SucreBlogApplication.class, args);
    }

}
