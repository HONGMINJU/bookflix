package com.bookflix.bookflix.book.dto.response;

import com.bookflix.bookflix.library.entity.Library;
import com.bookflix.bookflix.library.entity.NearLibrary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LibraryInfo {

    private String name;
    private double distance;
    private boolean have;
    private boolean canBorrow;

    @Builder
    public LibraryInfo(String name, double distance, boolean have, boolean canBorrow){
        this.name = name;
        this.distance = distance;
        this.have = have;
        this.canBorrow = canBorrow;
    }

    public static LibraryInfo of(NearLibrary nearLibrary, boolean have, boolean canBorrow){
        return LibraryInfo.builder()
                .name(nearLibrary.getLibrary().getName())
                .distance(nearLibrary.getDistance())
                .have(have)
                .canBorrow(canBorrow)
                .build();
    }
}
