package com.app.dodamdodam.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Criteria {
    private int page;
    private String keyword;
    private String status;
}
