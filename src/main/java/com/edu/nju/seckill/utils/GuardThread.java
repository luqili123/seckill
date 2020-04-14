package com.edu.nju.seckill.utils;

/**
 * @author LiuWen
 * @date 2020-3-21 10:03
 */
public class GuardThread extends Thread {
    private int timeOut;
    private String lockKey;
    private RedisUtil redisUtil;

    public GuardThread(int timeOut, String lockKey, RedisUtil redisUtil) {
        this.timeOut = timeOut;
        this.lockKey = lockKey;
        this.redisUtil = redisUtil;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(timeOut / 2 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            long expire = redisUtil.getExpire(lockKey);
            if (expire > 0) {
                redisUtil.expire(lockKey, expire + (timeOut / 2));
            }
        }
    }
}
