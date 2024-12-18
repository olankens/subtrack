package com.example.subtrack.club;

import java.util.ArrayList;
import java.util.List;

public class ClubController {
    private final List<ClubModel> clubs = new ArrayList<>();

    public void create(ClubModel club) {
        if (club != null && search(club.name) == null) clubs.add(club);
    }

    public ClubModel search(String name) {
        return clubs.stream()
                .filter(c -> c.name.equals(name))
                .findFirst()
                .orElse(null);
    }
}
