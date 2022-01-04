package com.winble.server.influencer.repository;

import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.domain.profile.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByIdAndInfluencer(Long id, Influencer influencer);
    List<Address> findAllByInfluencer(Influencer influencer);
}
