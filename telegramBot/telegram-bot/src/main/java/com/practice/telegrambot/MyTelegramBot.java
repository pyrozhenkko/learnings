package com.practice.telegrambot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;

@Component
public class MyTelegramBot  implements SpringLongPollingBot {
    @Override
    public String getBotToken() {
        return "";
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return null;
    }
}
