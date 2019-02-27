package com.zuhlke.cs.model;

import java.util.List;

public interface Shape {

    /**
     * Returns 1..n lines which define the shape.
     */
    List<Line> getLines();

    /**
     * Returns true if the shape intersects the
     * rectangular area defined by bounds.
     */
    boolean intersects(Rectangle bounds);
}
