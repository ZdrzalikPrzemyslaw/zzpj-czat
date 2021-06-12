package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import tech.pzjswpooks.zzpj.chat.api.common.I18n;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SendChatMessageRequestDTO {
    @NotNull(message = I18n.MESSAGE_EMPTY)
    @Size(min = 1, max = 4095, message = I18n.MESSAGE_SIZE_INVALID)
    private String message;

    public SendChatMessageRequestDTO(String message) {
        this.message = message;
    }

    public SendChatMessageRequestDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("message", message)
                .toString();
    }
}
