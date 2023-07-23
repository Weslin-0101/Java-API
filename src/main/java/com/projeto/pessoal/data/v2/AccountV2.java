package com.projeto.pessoal.data.v2;

import java.io.Serializable;
import java.util.Objects;

public class AccountV2 implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String password;
    private String confirmPassword;

    public AccountV2() {}

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountV2 accountV2 = (AccountV2) o;
        return Objects.equals(id, accountV2.id) && Objects.equals(name, accountV2.name) && Objects.equals(email, accountV2.email) && Objects.equals(password, accountV2.password) && Objects.equals(confirmPassword, accountV2.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, confirmPassword);
    }
}
