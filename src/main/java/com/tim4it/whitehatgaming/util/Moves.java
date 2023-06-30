package com.tim4it.whitehatgaming.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Moves {

    int sourceRow;
    int sourceColumn;

    int destinationRow;
    int destinationColumn;
}
