package com.harvey.work.controller;

import com.google.common.hash.PrimitiveSink;
import com.harvey.work.entity.Spike;
import com.harvey.work.util.BloomFilter;
import com.harvey.work.util.RedisUtil;
import com.google.common.hash.Funnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalTime;

/**
 * @program: Study
 * @ClassName : StudyController
 * @description: 学习SpringMVC
 * @author: Harvey
 * @create: 2020-02-21 16:12
 */
@Controller
@RequestMapping("/study")
@ResponseBody
public class StudyController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/checkNull")
    public String test(@Valid Spike spike) {
        return spike.getVin();
    }

    @GetMapping("/redis")
    public String test(){
        RedisUtil redisUtil = new RedisUtil(redisTemplate);
        Funnel<String> funnel = new Funnel<String>() {
            @Override
            public void funnel(String s, PrimitiveSink primitiveSink) {
            }
        };
        BloomFilter<String> bloomFilter = new BloomFilter<String>(funnel,10000,0.3d);
        RedisCallback<String> action = new RedisCallback<String>(){
            @Override
            public String  doInRedis(RedisConnection connection) throws DataAccessException {
                for (int i = 0; i <1000 ; i++) {
                    System.out.println(i);
                    redisUtil.addByBloomFilter(bloomFilter,"yhw","yanghanwen"+i);
                }
                return null;
            }
        };
        redisUtil.doByPipe(action);
        return redisUtil.includeByBloomFilter(bloomFilter,"yhw","yanghanwen500")+"";
    }

    @GetMapping("/redisAdd")
    public void redisAdd(){
        RedisUtil redisUtil = new RedisUtil(redisTemplate);
        LocalTime beginTime = LocalTime.now();
        for (int i = 0; i <1000 ; i++) {
            redisUtil.set(String.valueOf(i),String.valueOf(i));
        }
        LocalTime endTime = LocalTime.now();
        Duration duration = Duration.between(beginTime, endTime);
        System.out.println(duration.toMillis());
    }
}