package com.epam.socode.repository;

import com.epam.socode.domain.CodeFragment;

public interface CodeFragmentRepository {

    CodeFragment addCodeFragment(CodeFragment codeFragment);

    CodeFragment updateCodeFragment(CodeFragment codeFragment);

    CodeFragment findCodeFragmentByName(String name);

    CodeFragment findCodeFragmentById(String codeFragmentId);
}
