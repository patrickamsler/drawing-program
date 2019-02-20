package com.zuhlke.cs.model;

import com.zuhlke.cs.model.Line;
import com.zuhlke.cs.model.Rectangle;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

public class RectangleTest {

    @Test
    public void rectangleHasFourRightAngleLines() {
        // given
        Rectangle rectangle = new Rectangle(0, 0, 4, 5);

        // when
        List<Line> lines = rectangle.getLines();

        // then
        assertThat(lines, hasSize(4));
        assertThat(lines, containsInAnyOrder(
                new Line(0, 0, 4, 0),
                new Line(4, 0, 4, 5),
                new Line(4, 5, 0, 5),
                new Line(0, 5, 0, 0)
                )
        );
    }

}
