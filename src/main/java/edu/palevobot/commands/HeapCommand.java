package edu.palevobot.commands;

import edu.palevobot.entities.Palevo;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HeapCommand extends BotCommand {
    /**
     * Construct a command
     *
     * @param commandIdentifier the unique identifier of this command (e.g. the command string to
     *                          enter into chat)
     * @param description       the description of this command
     */
    public HeapCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        //Вытаскиваем все файлики(айдишки) из бд и формируем наши кнопочки. :)
//        try {
//            JdbcDao jdbcDao = (JdbcDao) new PalevoDaoFactory().getDao(DaoType.SQL);
//            for (Palevo palevo : (ArrayList<Palevo>) jdbcDao.getAll()) {
//                List<InlineKeyboardButton> rowInline = new ArrayList<>();
//                //Передаем ключи файлов в колбэки
//                rowInline.add(new InlineKeyboardButton()
//                        .setText(palevo.getTitle())
//                        .setCallbackData(Integer.toString(palevo.getId())));
//                rowsInline.add(rowInline);
//            }
//            jdbcDao.closeConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        markupInline.setKeyboard(rowsInline);

        SendMessage answer = new SendMessage();
        answer.setReplyMarkup(markupInline);
        answer.setChatId(chat.getId());
        answer.setText("Все файлики");
        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
