package com.winble.server.application.member.model.vo;

import com.winble.server.domain.model.member.entity.Member;
import com.winble.server.domain.model.member.entity.MemberStatus;
import com.winble.server.domain.model.member.entity.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class MemberVO implements UserDetails {

    private long id;
    private String memberLoginId;
    private String password;
    private Role role;
    private MemberStatus memberStatus;

    public MemberVO(Member entity) {
        this.id = entity.getMemberId();
        this.memberLoginId = entity.getMemberLoginId();
        this.password = entity.getPassword();
        this.role = entity.getRole();
        this.memberStatus = entity.getMemberStatus();
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
        return this.memberLoginId;
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
