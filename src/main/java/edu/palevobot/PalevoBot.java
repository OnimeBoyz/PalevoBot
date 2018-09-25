package edu.palevobot;

import edu.palevobot.commands.GetFileCommand;
import edu.palevobot.commands.HeapCommand;
import edu.palevobot.commands.StartCommand;
import edu.palevobot.dao.JdbcDao;
import edu.palevobot.dao.palevo.PalevoDaoFactory;
import edu.palevobot.entities.Palevo;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.sql.SQLException;

import static edu.palevobot.config.BotConfig.BOT_TOKEN;

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
        if(update.hasMessage() && update.getMessage().hasDocument()) {
            long chatId = update.getMessage().getChatId();
            Document document = update.getMessage().getDocument();
            String documentId = document.getFileId();
            String title = document.getFileName();
            try {
                JdbcDao jdbcDao = (JdbcDao) new PalevoDaoFactory().getDao("jdbc");
                jdbcDao.insert(new Palevo(0, title, "Описание", documentId, 0.0));
                jdbcDao.closeConnection();
                replyText("Загружен файлик - " + title, chatId);
            } catch (SQLException | TelegramApiException | IOException e) {
                e.printStackTrace();
            }
        }

        if(update.hasCallbackQuery()) {

            String callData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            try {
                try {
                    JdbcDao jdbcDao = (JdbcDao) new PalevoDaoFactory().getDao("jdbc");
                    Palevo palevo = (Palevo) jdbcDao.getById(Integer.parseInt(callData));
                    jdbcDao.closeConnection();
                    execute(new SendDocument()
                            .setChatId(chatId)
                            .setDocument(palevo.getDocument()));
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
