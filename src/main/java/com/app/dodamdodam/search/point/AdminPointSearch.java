package com.app.dodamdodam.search.point;

import com.app.dodamdodam.type.PointStatus;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class AdminPointSearch {
    private Integer pointAmount;
    private PointStatus pointStatus;
    private String memberId;
}
