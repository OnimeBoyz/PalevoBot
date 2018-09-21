package edu.palevobot;

import edu.palevobot.commands.GetFileCommand;
import edu.palevobot.commands.StartCommand;
import edu.palevobot.config.BotConfig;
import edu.palevobot.dao.PalevoDaoFactory;
import edu.palevobot.entities.Palevo;
import org.json.JSONObject;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static edu.palevobot.config.BotConfig.BOT_TOKEN;

public class PalevoBot extends TelegramLongPollingCommandBot {
    public PalevoBot(String botUsername) {
        super(botUsername);
        registerAll(new StartCommand("start", "Start"),
                new GetFileCommand("get_file", "GetFile"));
        registerDefaultAction((absSender, message) -> {
            SendMessage commandUnknownMessage = new SendMessage();
            commandUnknownMessage.setChatId(message.getChatId());
            commandUnknownMessage.setText("The command '" + message.getText() + "' is not known by this bot. ");
            try {
                absSender.execute(commandUnknownMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if(update.hasMessage() && update.getMessage().hasDocument()) {
            long chatId = update.getMessage().getChatId();
            Document document = update.getMessage().getDocument();
            String documentId = update.getMessage().getDocument().getFileId();
            try {
                new PalevoDaoFactory().getDao("jdbc").insert(new Palevo(0, "titleTest", "ХУЙ", documentId, 0.0));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
