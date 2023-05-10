package com.app.dodamdodam.search.point;

import com.app.dodamdodam.type.PointStatus;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class PointSearch {
    private Integer pointAmount;
    private PointStatus pointStatus;
}
