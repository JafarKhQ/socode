package com.epam.socode.repository;

import com.epam.socode.domain.Tag;

public interface TagRepository {

    Tag addTag(Tag tag);

    Tag findTagByName(String name);
}
