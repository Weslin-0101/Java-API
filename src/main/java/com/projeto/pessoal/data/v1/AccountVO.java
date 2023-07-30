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
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountVO personVO = (AccountVO) o;
        return Objects.equals(id, personVO.id) && Objects.equals(name, personVO.name) && Objects.equals(email, personVO.email) && Objects.equals(password, personVO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, email, password);
    }
}
