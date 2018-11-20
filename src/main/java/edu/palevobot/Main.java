package edu.palevobot;

import edu.palevobot.config.BotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static edu.palevobot.config.BotConfig.BOT_USERNAME;

@SpringBootApplication
@EnableJpaAuditing
public class Main {
    public static void main(String[] args) {
      }

    private void startBot(){
        try {
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            try {
                telegramBotsApi.registerBot(new PalevoBot(BOT_USERNAME));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("PalevoBot successfully started!");

    }
    private void startApi(String[] args){
        SpringApplication.run(Main.class, args);
    }
}
