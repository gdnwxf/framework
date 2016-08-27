package com.wch.domain;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private Long id;

    private String username;

    private String part;

    private Byte age;

    private Long dsadsa;

    private Long da;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part == null ? null : part.trim();
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Long getDsadsa() {
        return dsadsa;
    }

    public void setDsadsa(Long dsadsa) {
        this.dsadsa = dsadsa;
    }

    public Long getDa() {
        return da;
    }

    public void setDa(Long da) {
        this.da = da;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserInfo other = (UserInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPart() == null ? other.getPart() == null : this.getPart().equals(other.getPart()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getDsadsa() == null ? other.getDsadsa() == null : this.getDsadsa().equals(other.getDsadsa()))
            && (this.getDa() == null ? other.getDa() == null : this.getDa().equals(other.getDa()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPart() == null) ? 0 : getPart().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getDsadsa() == null) ? 0 : getDsadsa().hashCode());
        result = prime * result + ((getDa() == null) ? 0 : getDa().hashCode());
        return result;
    }
}