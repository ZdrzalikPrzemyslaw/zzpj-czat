package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import tech.pzjswpooks.zzpj.chat.api.common.I18n;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateChatRequestDto {
    @NotNull(message = I18n.CHAT_NAME_NULL)
    @Size(min = 1, max = 30, message = I18n.CHAT_NAME_INVALID_SIZE)
    private String name;

    public CreateChatRequestDto(String name) {
        this.name = name;
    }

    public CreateChatRequestDto() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
