package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import tech.pzjswpooks.zzpj.chat.api.common.I18n;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ChangeChatNameRequestDTO {
    @NotEmpty
    @Size(min = 1, max = 30, message = I18n.CHAT_NAME_INVALID_SIZE)
    private String name;

    public ChangeChatNameRequestDTO(@NotEmpty @Size(min = 1, max = 30, message = I18n.CHAT_NAME_INVALID_SIZE) String name) {
        this.name = name;
    }

    public ChangeChatNameRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
