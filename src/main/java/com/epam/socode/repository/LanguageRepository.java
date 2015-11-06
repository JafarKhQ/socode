package com.epam.socode.repository;

import com.epam.socode.domain.Language;

public interface LanguageRepository {

    Language addLanguage(Language language);

    Language findLanguageByName(String name);
}
