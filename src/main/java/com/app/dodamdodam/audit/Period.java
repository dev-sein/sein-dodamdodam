package com.app.dodamdodam.audit;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class Period {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    public void create(){
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void update(){
        this.updatedDate = LocalDateTime.now();
    }
}
