package tech.pzjswpooks.zzpj.chat.api.payloads.response;

import com.sun.istack.Nullable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;

import java.util.List;

public class SearchUserResponseDto {
    @Nullable
    private List<UsersResponseDTO> users;

    public SearchUserResponseDto(@Nullable List<UsersResponseDTO> users) {
        this.users = users;
    }

    public SearchUserResponseDto() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("users", users)
                .toString();
    }

    public List<UsersResponseDTO> getUsers() {
        return users;
    }


}
