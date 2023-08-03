package com.projeto.pessoal.data.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "name", "email", "password"})
public class AccountVO extends RepresentationModel<AccountVO> implements Serializable {

    private static final Long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;
    private String name;
    private String email;
    private String userName;
    private String password;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;

    public AccountVO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountVO accountVO = (AccountVO) o;
        return Objects.equals(id, accountVO.id) && Objects.equals(name, accountVO.name) && Objects.equals(email, accountVO.email) && Objects.equals(userName, accountVO.userName) && Objects.equals(password, accountVO.password) && Objects.equals(accountNonExpired, accountVO.accountNonExpired) && Objects.equals(accountNonLocked, accountVO.accountNonLocked) && Objects.equals(credentialsNonExpired, accountVO.credentialsNonExpired) && Objects.equals(enabled, accountVO.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, email, userName, password, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
    }
}
