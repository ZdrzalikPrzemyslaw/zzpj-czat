package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateChatRequestDto {
//    @NotEmpty
//    @Size(min = 6, max = 32, message = "Invalid size of username")
//    private String username;
    @NotNull
    @Size(min = 1, max = 50, message = "Invalid size of name")
    private String name;

    public CreateChatRequestDto(@NotNull @Size(min = 1, max = 50, message = "Invalid size of name") String name) {
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
}
