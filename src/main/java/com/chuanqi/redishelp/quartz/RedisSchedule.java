package com.chuanqi.redishelp.quartz;

import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @author xuchuanqi
 * @date 2022/8/20 19:36.
 */
@Component
public class RedisSchedule {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    public void sttas() throws IOException {
        File directory = new File("");
        String absolutePath = directory.getAbsolutePath();
        // 直接事先放在linux的系统文件中执行。 TODO 对于放在jar的文件中，还没有找到合适的方法，以后有时间就在弄一下吧。
        Path path = Paths.get("/root/new 1.txt");
        Object[] objects = Files.lines(path).toArray();
        for (int i = 0; i < objects.length; i++) {
            String str = (String) objects[i];
            if (str.startsWith("ship2tid") ||
                    str.startsWith("ship2shipTown") ||
                    str.startsWith("tid2")) {
                continue;
            }

            int time = (int) (1 + (Math.random() * ((10 - 1) + 1)));
            System.out.println(str + " 的过期时间分钟数为：" + time);
            redisTemplate.opsForValue().getAndExpire(str, time, TimeUnit.MINUTES);
        }

    }
}
