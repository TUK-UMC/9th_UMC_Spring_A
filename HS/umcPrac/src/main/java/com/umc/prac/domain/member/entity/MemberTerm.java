package com.umc.prac.domain.member.entity;

import com.umc.prac.domain.term.entity.Term;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_term")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MemberTerm {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberTermId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id")
    private Term term;
}
