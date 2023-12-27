package org.choongang.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class HashTag {
    @Id
    private String tag;

    @ToString.Exclude
    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private List<BoardData> items=new ArrayList<>();
}
