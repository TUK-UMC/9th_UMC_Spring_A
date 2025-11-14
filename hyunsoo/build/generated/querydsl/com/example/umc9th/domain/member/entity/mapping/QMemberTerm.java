package com.example.umc9th.domain.member.entity.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberTerm is a Querydsl query type for MemberTerm
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberTerm extends EntityPathBase<MemberTerm> {

    private static final long serialVersionUID = 551671500L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberTerm memberTerm = new QMemberTerm("memberTerm");

    public final com.example.umc9th.global.jpa.QBaseEntity _super = new com.example.umc9th.global.jpa.QBaseEntity(this);

    public final BooleanPath agreed = createBoolean("agreed");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.umc9th.domain.member.entity.QMember member;

    public final com.example.umc9th.domain.term.entity.QTerm term;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberTerm(String variable) {
        this(MemberTerm.class, forVariable(variable), INITS);
    }

    public QMemberTerm(Path<? extends MemberTerm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberTerm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberTerm(PathMetadata metadata, PathInits inits) {
        this(MemberTerm.class, metadata, inits);
    }

    public QMemberTerm(Class<? extends MemberTerm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.umc9th.domain.member.entity.QMember(forProperty("member")) : null;
        this.term = inits.isInitialized("term") ? new com.example.umc9th.domain.term.entity.QTerm(forProperty("term")) : null;
    }

}

