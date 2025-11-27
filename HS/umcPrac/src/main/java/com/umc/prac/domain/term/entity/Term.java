package com.umc.prac.domain.term.entity;

import com.umc.prac.domain.member.entity.MemberTerm;
import com.umc.prac.domain.member.enumtype.TermType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "term")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Term {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termId;

    @Enumerated(EnumType.STRING)
    private TermType name; // AGE, SERVICE, ...

    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTerm> memberTerms = new ArrayList<>();
}
