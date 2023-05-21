package com.app.dodamdodam.repository.file;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileQueryDslImpl implements FileQueryDsl {
    private final JPAQueryFactory query;

}
