package edu.palevobot;

import edu.palevobot.commands.GetFileCommand;
import edu.palevobot.commands.HeapCommand;
import edu.palevobot.commands.StartCommand;
import edu.palevobot.config.BotConfig;
import edu.palevobot.dao.PalevoDaoFactory;
import edu.palevobot.entities.Palevo;
import org.json.JSONObject;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
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
import static java.lang.Math.toIntExact;

public class PalevoBot extends TelegramLongPollingCommandBot {
    public PalevoBot(String botUsername) {
        super(botUsername);
        registerAll(new StartCommand("start", "Start"),
                new GetFileCommand("get_file", "GetFile"),
                new HeapCommand("heap", "GetFileHeap")
        );
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
        System.out.println("nice");
        if(update.hasMessage() && update.getMessage().hasDocument()) {
            long chatId = update.getMessage().getChatId();
            Document document = update.getMessage().getDocument();
            String documentId = update.getMessage().getDocument().getFileId();
            String title = update.getMessage().getDocument().getFileName();
            try {
                new PalevoDaoFactory().getDao("jdbc").insert(new Palevo(0, title, "ХУЙ", documentId, 0.0));
                replyText("Загружен файлик - " + title, chatId);
            } catch (SQLException | TelegramApiException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(update.hasCallbackQuery()) {

            String callData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            try {
                try {
                    execute(new SendDocument()
                            .setChatId(chatId)
                            .setDocument(new PalevoDaoFactory()
                                    .getDao("jdbc")
                                    .getById(Integer.parseInt(callData)).getDocument()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    private void replyText(String message, long chatId) throws TelegramApiException {
        execute(new SendMessage()
                .setChatId(chatId)
                .setText(message)
        );
    }
    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
