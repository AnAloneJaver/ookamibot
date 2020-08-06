package top.kagerou.ookamibot.eventlistener;

import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.message.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FriendEventListener extends SimpleListenerHost {

    Logger log = LoggerFactory.getLogger(FriendEventListener.class);

    @EventHandler
    public void onMessage(FriendMessageEvent event) {
        MessageChain messageChain = event.getMessage();
        PlainText plainText = messageChain.first(PlainText.Key);
        if (plainText != null) {
            log.info("监听到一条私聊消息，plianText为" + plainText.contentToString());
            log.info("plianText类型" + plainText.contentToString().getClass());
        }

    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        log.error("私聊消息处理错误" + exception.getMessage());
    }
}