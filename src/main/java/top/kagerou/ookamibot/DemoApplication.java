package top.kagerou.ookamibot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import top.kagerou.ookamibot.eventlistener.FriendEventListener;
import top.kagerou.ookamibot.eventlistener.GroupEventListener;
import top.kagerou.ookamibot.helper.ApplicationContextHelper;
import top.kagerou.ookamibot.miraibot.Robotstart;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(DemoApplication.class);

		SpringApplication.run(DemoApplication.class, args);
		logger.info("SpringBoot启动机器人");
		Robotstart.start(ApplicationContextHelper.getBean(FriendEventListener.class),
				ApplicationContextHelper.getBean(GroupEventListener.class));
	}

}
