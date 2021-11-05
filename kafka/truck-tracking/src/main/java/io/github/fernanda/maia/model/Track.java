package io.github.fernanda.maia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {

    private long id;
    private String latitude;
    private String longitude;
}
