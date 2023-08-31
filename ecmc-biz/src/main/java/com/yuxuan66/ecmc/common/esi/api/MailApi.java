package com.yuxuan66.ecmc.common.esi.api;

import com.yuxuan66.ecmc.common.esi.EsiHelper;
import com.yuxuan66.ecmc.modules.account.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import net.troja.eve.esi.model.Mail;

import java.util.ArrayList;
import java.util.List;

/**
 * 对 mail api扩展
 *
 * @author Sir丶雨轩
 * @since 2022/12/14
 */
public class MailApi {

    /**
     * 邮件发送
     *
     * @param account          发件人
     * @param subject       发件主题
     * @param body          发件内容
     * @param recipientList 收件人
     */
    @SneakyThrows
    public static void send(UserAccount account, String subject, String body, List<Recipient> recipientList) {
        Mail mail = new Mail();
        mail.setSubject(subject);
        mail.setBody(body);
        List<net.troja.eve.esi.model.Recipient> realRecipientList = new ArrayList<>();
        for (Recipient recipient : recipientList) {
            net.troja.eve.esi.model.Recipient sendRecipient = new net.troja.eve.esi.model.Recipient();
            sendRecipient.setRecipientId(recipient.getId());
            sendRecipient.setRecipientTypeString(recipient.getType().toString());
            realRecipientList.add(sendRecipient);
        }
        mail.setRecipients(realRecipientList);
        new net.troja.eve.esi.api.MailApi(account.esiClient()).postCharactersCharacterIdMailAsync(account.getCharacterId(), mail, "", "", EsiHelper.defaultCallback());
    }


    /**
     * 收件人信息
     */
    @Data
    @AllArgsConstructor
    public static class Recipient {
        private Integer id;
        private net.troja.eve.esi.model.Recipient.RecipientTypeEnum type;
    }
}
