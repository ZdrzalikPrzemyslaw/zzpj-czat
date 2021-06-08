package tech.pzjswpooks.zzpj.chat.api.payloads.response;

import com.sun.istack.Nullable;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CreateChatResponseDto {
    @Nullable
    private MessageResponseDto messageResponseDto;
    private Boolean wasSuccessful;

    public CreateChatResponseDto(@Nullable MessageResponseDto messageResponseDto, Boolean wasSuccessful) {
        this.messageResponseDto = messageResponseDto;
        this.wasSuccessful = wasSuccessful;
    }

    public CreateChatResponseDto() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("messageResponseDto", messageResponseDto)
                .append("wasSuccessful", wasSuccessful)
                .toString();
    }

    @Nullable
    public MessageResponseDto getMessageResponseDto() {
        return messageResponseDto;
    }

    public Boolean getWasSuccessful() {
        return wasSuccessful;
    }
}