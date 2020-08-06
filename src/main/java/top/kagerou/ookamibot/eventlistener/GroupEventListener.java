package top.kagerou.ookamibot.eventlistener;

import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.LightApp;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import top.kagerou.ookamibot.bean.LoliconImageBean;
import top.kagerou.ookamibot.features.groupfeatures.Menu;
import top.kagerou.ookamibot.features.groupfeatures.Setu;
import top.kagerou.ookamibot.features.groupfeatures.Shadiao;
import top.kagerou.ookamibot.features.groupfeatures.Wangyiyun;

import java.io.File;

import org.jetbrains.annotations.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GroupEventListener extends SimpleListenerHost {

    Logger log = LoggerFactory.getLogger(GroupEventListener.class);

    @EventHandler
    public void onMessage(GroupMessageEvent event) {
        MessageChain messageChain = event.getMessage();
        String groupMsg = messageChain.contentToString();
        if (groupMsg != null) {
            log.info("监听到一条群消息，groupMsg为" + groupMsg);
            if (groupMsg.contains("-功能")) {
                String sendMsg = Menu.getMenu();
                event.getGroup().sendMessage(sendMsg);
            } else if (groupMsg.contains("-Mirai功能")) {
                String sendMsg = Menu.getFeature();
                event.getGroup().sendMessage(sendMsg);
            } else if (groupMsg.equals("骂我")) {
                String sendMsg = Shadiao.getNMSL();
                event.getGroup().sendMessage(MessageUtils.newChain(sendMsg).plus(new At(event.getSender())));
            } else if (groupMsg.equals("夸我")) {
                String sendMsg = Shadiao.getCHP();
                event.getGroup().sendMessage(MessageUtils.newChain(sendMsg).plus(new At(event.getSender())));
            } else if (groupMsg.equals("来张涩图")) {
                String r18 = "0";
                // 获取imageBean的基本信息
                LoliconImageBean imageBean = Setu.getLoliconImage(r18);

                String imageTitle = imageBean.getTitle();
                String imageUrl = imageBean.getUrl();
                String sendMsg = "神秘链接：" + imageUrl + "\n";

                File imageFile = Setu.saveImageFile(imageUrl, imageTitle);
                // 响应时间有点长，写一个文本消息来缓冲一下使用时的体验，如果处理时间不长可以不用。
                // event.getGroup().sendMessage(MessageUtils.newChain(sendMsg).plus(new
                // At(event.getSender())));
                Image image = event.getGroup().uploadImage(imageFile);
                String imageId = image.getImageId();
                event.getGroup().sendMessage(MessageUtils.newImage(imageId));
            } else if (groupMsg.equals("网抑云")) {
                String sendMsg = Wangyiyun.getWangyiyunComment();
                event.getGroup().sendMessage(sendMsg);
            } else if (groupMsg.contains("点歌")) {
                if (groupMsg.substring(0, 2).equals("点歌")) {
                    String songName = groupMsg.substring(2, groupMsg.length());
                    MessageChain ms = MessageUtils.newChain(new LightApp(Wangyiyun.getCloudMusicJsonContent(songName)));
                    event.getGroup().sendMessage(ms);
                }
            }
        }

    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        log.error("群消息处理错误!" + exception.getMessage());
    }
}