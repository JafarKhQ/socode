package com.epam.socode.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.socode.domain.CodeFragment;
import com.epam.socode.domain.Language;
import com.epam.socode.domain.Profile;
import com.epam.socode.repository.CodeFragmentRepository;
import com.epam.socode.request.CodeFragmentData;
import com.epam.socode.request.LanguageData;
import com.epam.socode.service.AuthenticationService;
import com.epam.socode.service.CodeFragmentService;
import com.epam.socode.service.ProfileService;

public class CodeFragmentServiceImpl implements CodeFragmentService{

    @Autowired
    CodeFragmentRepository codeFragmentRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AuthenticationService authenticationService;
    
    @Override
    public CodeFragment create(CodeFragmentData codeFragmentData) {
        String profileId = authenticationService.findProfileIdByToken(codeFragmentData.getToken());
        Profile profile = profileService.findProfileById(profileId);
        
        CodeFragment codeFragment = new CodeFragment();
        codeFragment.setProfile(profile);
        codeFragment.setCreationTime(new Date());
        codeFragment.setName(codeFragmentData.getName());
        codeFragment.setBody(codeFragmentData.getBody());
        

        return null;
    }

    @Override
    public CodeFragment update(CodeFragment codeFragment) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CodeFragment get(UUID fragmentId) {
        // TODO Auto-generated method stub
        return null;
    }

}
