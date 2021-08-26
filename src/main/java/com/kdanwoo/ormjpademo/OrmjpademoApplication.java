package com.kdanwoo.ormjpademo;

import com.kdanwoo.ormjpademo.jpademo.Hello;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrmjpademoApplication {

    public static void main(String[] args) {

        Hello hello = new Hello();
        hello.setData("data");
        String data = hello.getData();
        System.out.println(data);


        SpringApplication.run(OrmjpademoApplication.class, args);
    }

}
