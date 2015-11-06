package com.epam.socode.service;

import java.util.UUID;

import com.epam.socode.domain.CodeFragment;
import com.epam.socode.domain.Profile;
import com.epam.socode.request.CodeFragmentData;

public interface CodeFragmentService {
    CodeFragment create(CodeFragmentData codeFragmentData);

    CodeFragment update(CodeFragment codeFragment);

    CodeFragment get(UUID fragmentId);
}
