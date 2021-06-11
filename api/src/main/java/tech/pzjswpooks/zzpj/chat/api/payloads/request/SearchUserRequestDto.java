package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import tech.pzjswpooks.zzpj.chat.api.common.I18n;

import javax.validation.constraints.NotNull;

public class SearchUserRequestDto {

    @NotNull(message = I18n.FILTER_EMPTY)
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
