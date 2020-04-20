package com.edu.nju.seckill;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.edu.nju.seckill.dao.OrderMapper;
import com.edu.nju.seckill.domain.Order;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.UserInfo;
import com.edu.nju.seckill.domain.dto.UserParam;
import com.edu.nju.seckill.service.NavigationService;
import com.edu.nju.seckill.service.OrderService;
import com.edu.nju.seckill.service.SeckillGoodsService;
import com.edu.nju.seckill.service.UserService;
import com.edu.nju.seckill.utils.OrderIdUtils;
import com.edu.nju.seckill.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@RunWith(SpringRunner.class)
class SeckillApplicationTests {

    @Resource
    RedisUtil redisUtil;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    NavigationService navigationService;

    @Autowired
    SeckillGoodsService seckillGoodsService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderMapper orderMapper;

    @Test
    void contextLoads() {

    }
    @Test
    public void testRandomName(){
        System.out.println(UUID.randomUUID().toString().substring(0,8));
    }


    @Test
    public void testRedis(){
//        User user=new User();
//        for(int i=0;i<10;i++) {
//            user.setName(i+"");
//            user.setPassword("123123");
//            user.setPhone("15651879552");
//            redisUtil.hset("user",i+"", user);
//        }
//        redisUtil.set("test","hello",100);
//        System.out.println( redisUtil.getExpire("test"));
//        redisUtil.hset("htest","1","hello",100);
//        System.out.println(redisUtil.getExpire("htest"));
        User user=new User();
        Map<String,Object> userMap=new HashMap<>();
        userMap.put("uid",1);
        userMap.put("name","lql");
        userMap.put("password","123456");
        userMap.put("email","sss");
        userMap.put("addressId",1);
        userMap.put("role",1);
        userMap.put("deleteFlag",1);

//        user.setUid((long) 1);
        user.setName("lql");
        user.setPassword("123123");
        user.setPhone("15651879552");
        redisUtil.saveUser(user,"user:2",180);
        System.out.println(redisUtil.getExpire("user:2"));
        System.out.println(redisUtil.getUser("user:2"));
    }

    @Test
    public void testJwt(){
        //生成token
        Map<String,Object> header=new HashMap<String, Object>() ;
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        String token= JWT.create()
                .withHeader(header)// 设置头部信息 Header
                .withIssuer("Service")//设置 载荷 签名是有谁生成 例如 服务器
                .withSubject("login")//设置 载荷 签名的主题
                .withAudience("client")//设置 载荷 签名的观众 也可以理解谁接受签名的
                // .withNotBefore(new Date())//设置 载荷 定义在什么时间之前，该jwt都是不可用的.
                .withIssuedAt(new Date(System.currentTimeMillis())) //设置 载荷 生成签名的时间
                .withExpiresAt(new Date(System.currentTimeMillis()+60000))//设置 载荷 签名过期的时间
                .withClaim("user","123456")//设置自定义信息
                .withClaim("pass","123456")
                .sign(Algorithm.HMAC256("seckill"));//签名 Signature,加盐
        System.out.println(token);

        //验证token信息
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256("seckill"))
                .withIssuer("Service")//不添加 .withIssuer("SERVICE") 也是可以获取 JWTVerifier 。
                .build();
        DecodedJWT decodedJWT=jwtVerifier.verify(token);
        System.out.println("header "+decodedJWT.getHeader());
        System.out.println("payload "+decodedJWT.getPayload());
        System.out.println("Signature "+decodedJWT.getSignature());
        System.out.println("Subject "+decodedJWT.getSubject());
        System.out.println("Token "+decodedJWT.getToken());
        System.out.println("Algorithm "+decodedJWT.getAlgorithm());
        System.out.println("Audience "+decodedJWT.getAudience());
        System.out.println("Claims "+decodedJWT.getClaims());
        System.out.println("Claim "+decodedJWT.getClaim("user").asString());
        System.out.println("ContentType "+decodedJWT.getContentType());
        System.out.println("ExpiresAt "+decodedJWT.getExpiresAt());
        System.out.println("IssuedAt"+decodedJWT.getIssuedAt());


        System.out.println("-----------------------------------------");
        Map<String, Claim> map=decodedJWT.getClaims();
        for (Map.Entry<String, Claim> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().asString());
        }
        //

    }

    @Test
    public void testSecurity(){
        String pass="123456";
        String hash=encoder.encode(pass);
        System.out.println(encoder.matches(pass,hash));
    }

    @Test
    public void testNavigation(){

        System.out.println(navigationService.getAllNavItems());
    }

    @Test
    public void testSeckillGoodsResult(){
        System.out.println(seckillGoodsService.getSeckillList());
    }

    @Test
    public void testGetTableItems(){
        System.out.println(navigationService.getTableItems());
    }

    @Test
    public void testselectSeckillList(){

//        System.out.println(seckillGoodsService.getSeckillList());
//        System.out.println(seckillGoodsService.getLatestSeckillGoods());
        System.out.println(seckillGoodsService.getSeckillBySgid(7l));
    }

    @Test
    public void testSnowFlake(){
        for (int i = 0; i < 30; i++) {
            System.out.println(OrderIdUtils.getInstance().nextId());
        }
    }
    @Test
    public void testChangePwd(){
//        UserParam userParam=new UserParam();
//        userParam.setPhone("1565187952");
//        userParam.setPassword(encoder.encode("123456"));
//
//        System.out.println(userService.updatePwd(userParam));
    }

    @Test
    public void testupdateInfo(){
//        UserInfo userInfo=new UserInfo();
//        userInfo.setName("lql");
//        userInfo.setEmail("1036110216@qq.com");
//        userInfo.setPhone("15651879552");
//        System.out.println( userService.updateInfo(userInfo));

    }

//    @Test
//    public void testCreateOrder(){
//        OrderIdUtils orderIdUtils=OrderIdUtils.getInstance();
//        Long oid= orderIdUtils.nextId();
//        System.out.println(oid);
//        Order order=new Order();
//        order.setOid(oid);
//        order.setUid(11L);
//        order.setGid(14L);
//        order.setReceiverName("lql");
//        order.setReceiverPhone("12332112323");
//        order.setAddress("江苏省南京市南京大学");
//        order.setCount(1);
//        order.setPrice(1000.1d);
//        order.setCreateTime(new Date(System.currentTimeMillis()));
//        order.setStatus(5);
//        order.setSeckillFlag(0);
//        order.setPayTime(null);
//        order.setPayType(null);
//        order.setPostcode("123");
//        order.setSendTime(null);
//        System.out.println(order);
//        System.out.println(orderMapper.insert(order));
//
//    }
//    @Test
//    public void testDeleteOrder(){
//
//        System.out.println(orderService.deleteByOid(1226506380121870336l));
//    }

    @Test
    public void testGetOrderByStatus(){
        System.out.println( orderService.getOrderByStatus(1));

    }

    @Test
    public void test() {
        // OOM: out of memory
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
//            threadPool.submit();
        }
        threadPool.shutdown();

    }

    @Test
    public void testUpdateRemainCountBySgid(){
        System.out.println(seckillGoodsService.updateSeckillGoodsRemainCount(10,1));

    }
}
