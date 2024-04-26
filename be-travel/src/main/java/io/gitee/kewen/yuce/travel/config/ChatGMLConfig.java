package io.gitee.kewen.yuce.travel.config;

import cn.zrgzs.chatglm.session.OpenAiSession;
import cn.zrgzs.chatglm.session.defaults.DefaultOpenAiSessionFactory;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/15 13:35
 */
@Configuration
public class ChatGMLConfig {

    @Bean
    public OpenAiSession chatgml() {
        // 1.配置文件
        cn.zrgzs.chatglm.session.Configuration configuration = new cn.zrgzs.chatglm.session.Configuration();
        configuration.setApiSecretKey("86c0ec9b0753c1aaf2e1e0fd4f9869c3.jcpUxp7QzJj3krCp");

        // 2.会话工厂
        DefaultOpenAiSessionFactory defaultOpenAiSessionFactory = new DefaultOpenAiSessionFactory(configuration);
        OpenAiSession openAiSession = defaultOpenAiSessionFactory.openSession();
        return openAiSession;
    }
}
