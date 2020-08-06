package top.kagerou.ookamibot.miraibot;

import java.io.File;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.SystemDeviceInfoKt;
import top.kagerou.ookamibot.eventlistener.FriendEventListener;
import top.kagerou.ookamibot.eventlistener.GroupEventListener;

public class Robotstart {

    public static Bot bot = null;

    static {
        bot = BotFactoryJvm.newBot(1L, "****", new BotConfiguration() {
            {
                fileBasedDeviceInfo("deviceInfo.json");
            }
        });

        bot.login();
    }

    public static void start(FriendEventListener friendEventListener, GroupEventListener groupEventListener) {

        bot.getFriends().forEach(friend -> System.out.println(friend.getId() + ":" + friend.getNick()));
        bot.getGroups().forEach(group -> System.out.println(group.getId() + ":" + group.getName()));

        Events.registerEvents(bot, friendEventListener);
        Events.registerEvents(bot, groupEventListener);
        bot.join();
    }
}