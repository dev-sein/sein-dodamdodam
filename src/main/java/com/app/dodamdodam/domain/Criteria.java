package com.app.dodamdodam.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Data
public class Criteria {
    private int page;
    private String keyword;
    private String status;
}
