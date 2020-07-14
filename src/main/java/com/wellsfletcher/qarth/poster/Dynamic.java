package com.wellsfletcher.qarth.poster;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;

import java.util.List;

/**
 * Allows functionality for an object to change during the day.
 */
public interface Dynamic {
    public void update(LocalDateTime date);
}
