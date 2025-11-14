package com.example.umc9th.domain.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFood is a Querydsl query type for Food
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFood extends EntityPathBase<Food> {

    private static final long serialVersionUID = -922420760L;

    public static final QFood food = new QFood("food");

    public final com.example.umc9th.global.jpa.QBaseEntity _super = new com.example.umc9th.global.jpa.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.example.umc9th.domain.member.entity.mapping.MemberFood, com.example.umc9th.domain.member.entity.mapping.QMemberFood> memberFoods = this.<com.example.umc9th.domain.member.entity.mapping.MemberFood, com.example.umc9th.domain.member.entity.mapping.QMemberFood>createList("memberFoods", com.example.umc9th.domain.member.entity.mapping.MemberFood.class, com.example.umc9th.domain.member.entity.mapping.QMemberFood.class, PathInits.DIRECT2);

    public final EnumPath<com.example.umc9th.domain.food.enums.FoodName> name = createEnum("name", com.example.umc9th.domain.food.enums.FoodName.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QFood(String variable) {
        super(Food.class, forVariable(variable));
    }

    public QFood(Path<? extends Food> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFood(PathMetadata metadata) {
        super(Food.class, metadata);
    }

}

