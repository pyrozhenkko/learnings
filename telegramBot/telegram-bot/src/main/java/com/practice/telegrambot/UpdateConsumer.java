package com.practice.telegrambot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class UpdateConsumer implements LongPollingSingleThreadUpdateConsumer {
    private final static Logger log = LoggerFactory.getLogger(UpdateConsumer.class);

    @Override
    public void consume(Update update) {
        log.info("Прийшло повідомлення %s від %s", update.getMessage().getChat().getId(), update.getMessage().getText());
        update.getMessage().getText();
        update.getMessage().getChatId();

    }
}
