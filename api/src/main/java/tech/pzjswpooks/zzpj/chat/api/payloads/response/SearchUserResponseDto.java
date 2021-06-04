package tech.pzjswpooks.zzpj.chat.api.payloads.response;

import com.sun.istack.Nullable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;

import java.util.List;

public class SearchUserResponseDto {
    @Nullable
    private MessageResponseDto messageResponseDto;
    private Boolean wasSuccessful;
    @Nullable
    private List<UsersEntity> usersEntities;

    public SearchUserResponseDto(@Nullable List<UsersEntity> usersEntities, @Nullable MessageResponseDto messageResponseDto, Boolean wasSuccessful) {
        this.usersEntities = usersEntities;
        this.messageResponseDto = messageResponseDto;
        this.wasSuccessful = wasSuccessful;
    }

    public SearchUserResponseDto() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("users", usersEntities)
                .append("messageResponseDto", messageResponseDto)
                .append("wasSuccessful", wasSuccessful)
                .toString();
    }

    public List<UsersEntity> getUsersEntities() {
        return usersEntities;
    }

    @Nullable
    public MessageResponseDto getMessageResponseDto() {
        return messageResponseDto;
    }

    public Boolean getWasSuccessful() {
        return wasSuccessful;
    }
}
