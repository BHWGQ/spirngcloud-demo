package io.gitee.kewen.yuce.travel.consumer;

import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.feign.PortalClient;
import io.gitee.kewen.yuce.common.model.dto.req.TripInsertReq;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

import static io.gitee.kewen.yuce.common.consts.RabbitMqConsts.DELAYED_QUEUE_NAME;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/12 17:24
 */
@Component
public class Consumer {
    @Resource
    private JavaMailSenderImpl mailSender;

    @Resource
    private PortalClient portalClient;

    @RabbitListener(queues = DELAYED_QUEUE_NAME)
    public void receiveDelayedQueue(TripInsertReq tripInsertReq){
        Result<String> email = portalClient.getEmail(tripInsertReq.getUserId());
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("wgq18020445067@163.com");
        simpleMailMessage.setTo(email.getData());
        simpleMailMessage.setSubject("出发通知");
        simpleMailMessage.setText("您预定的行程即将到达预定时间" + tripInsertReq.getCreateTime() +"，请准备出发");
        mailSender.send(simpleMailMessage);
    }
}
