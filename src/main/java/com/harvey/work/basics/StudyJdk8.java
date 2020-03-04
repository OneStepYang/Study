package com.harvey.work.basics;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @program: Study
 * @ClassName : StudyJdk8
 * @description: 学习jdk8的一些新东西
 * @author: Harvey
 * @create: 2020-02-22 12:23
 */
public class StudyJdk8 {
    public static void main(String[] args) {

        LocalTime localtime = LocalTime.now();
        Duration duration = Duration.between(localtime, localtime);
    }
}