package com.epam.socode.service.impl;

import com.epam.socode.domain.Profile;
import com.epam.socode.repository.ProfileRepository;
import com.epam.socode.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jafar_qaddoumi
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile addProfile(Profile profile) {
        return profileRepository.addProfile(profile);
    }
}
