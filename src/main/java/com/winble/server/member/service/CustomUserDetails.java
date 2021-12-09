package com.winble.server.member.service;

import com.winble.server.member.domain.Influencer;
import com.winble.server.member.domain.enumeration.MemberStatus;
import com.winble.server.member.domain.enumeration.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {

    private long id;
    private String email;
    private String password;
    private Role role;
    private MemberStatus memberStatus;

    public CustomUserDetails(Influencer entity) {
        this.id = entity.getId();
        this.email = entity.getLoginId();
        this.password = entity.getPassword();
        this.role = entity.getRole();
        this.memberStatus = entity.getStatus();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role.getKey()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
