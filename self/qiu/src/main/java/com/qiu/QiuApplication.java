package com.qiu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@ServletComponentScan
@SpringBootApplication
public class QiuApplication {

    public static void main(String[] args) {
        SpringApplication.run(QiuApplication.class, args);
        System.out.println("——————————Success———————————");
        System.out.println("|                          |");
        System.out.println("|                          |");
        System.out.println("|                          |");
        System.out.println("|     System running…………   |");
        System.out.println("|                          |");
        System.out.println("|                          |");
        System.out.println("|                          |");
        System.out.println("————————————————————————————");
    }
}
