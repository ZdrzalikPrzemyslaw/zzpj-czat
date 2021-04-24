package tech.pzjswpooks.zzpj.chat.api.payloads.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MessageResponseDto {
    private String message;

    public MessageResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("message", message)
                .toString();
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
