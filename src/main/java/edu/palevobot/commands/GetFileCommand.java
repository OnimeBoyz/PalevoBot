package edu.palevobot.commands;

import edu.palevobot.dao.JdbcDao;
import edu.palevobot.dao.palevo.PalevoDaoFactory;
import edu.palevobot.dao.user.DaoType;
import edu.palevobot.entities.Palevo;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public class GetFileCommand extends BotCommand {
    /**
     * Construct a command
     *
     * @param commandIdentifier the unique identifier of this command (e.g. the command string to
     *                          enter into chat)
     * @param description       the description of this command
     */
    public GetFileCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String documentId = "";
        if(arguments.length == 0)
            return;
        try {
            JdbcDao jdbcDao = (JdbcDao) new PalevoDaoFactory().getDao(DaoType.SQL);
            Palevo palevo = (Palevo) jdbcDao.getById(Integer.parseInt(arguments[0]));
            documentId = palevo.getDocument();
            jdbcDao.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(documentId.equals(""))
            return;
        try {
            absSender.execute(new SendDocument()
                    .setChatId(chat.getId())
                    .setDocument(documentId));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
