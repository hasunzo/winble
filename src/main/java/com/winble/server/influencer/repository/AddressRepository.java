package com.winble.server.influencer.repository;

import com.winble.server.influencer.domain.profile.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
