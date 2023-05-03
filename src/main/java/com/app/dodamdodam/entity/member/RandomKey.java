package com.app.dodamdodam.entity.member;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_RANDOM_KEY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RandomKey {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private Long randomKey;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
