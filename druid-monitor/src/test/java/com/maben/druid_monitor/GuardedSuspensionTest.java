package com.maben.druid_monitor;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "m.GuardedSuspension")
public class GuardedSuspensionTest {

    /**
     *线程1 等待 线程2 的下载结果
     */
    public static void main(String[] args) {
        final GuardedObject guardedObject = new GuardedObject();
        new Thread(() -> {
            log.info("等待结果");
            final List<String> result = (List<String>) guardedObject.get(TimeUnit.SECONDS.toMillis(2));
            if (Objects.isNull(result)){
                log.info("没有获得结果！");
            }else {
                log.info("结果大小：{}", result.size());
            }
        }, "t1").start();
        new Thread(() -> {
            log.info("执行下载");
            final List<String> download;
            try {
                download = Downloader.download();
                TimeUnit.SECONDS.sleep(3);
                guardedObject.complete(download);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }

}

class GuardedObject {
    /**
     * 结果
     */
    private Object response;

    /**
     * 获取结果
     */
    public Object get() {
        synchronized (this) {
            while (response == null) {
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }
    /**
     * 获取结果
     */
    public Object get(long millis) {
        synchronized (this) {
            long base = System.currentTimeMillis();
            long now = 0;
            while (response == null) {
                try {
                    long delay = millis - now;
                    if (delay <= 0) {
                        break;
                    }
                    //防止虚假唤醒
                    this.wait(delay);
                    now = System.currentTimeMillis() - base;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    /**
     * 产生结果
     */
    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}
/**
 * 下载工具类
 */
class Downloader {
    public static List<String> download() throws Exception {
        final HttpURLConnection connection = (HttpURLConnection) new URL("http://www.baidu.com").openConnection();
        final List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        }
        return result;
    }
}