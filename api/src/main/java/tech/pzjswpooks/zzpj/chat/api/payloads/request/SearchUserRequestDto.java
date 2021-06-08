package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import javax.validation.constraints.NotNull;

public class SearchUserRequestDto {

    @NotNull
    private String filter;

    public SearchUserRequestDto() {
    }

    public SearchUserRequestDto(String filter) {
        this.filter = filter;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
