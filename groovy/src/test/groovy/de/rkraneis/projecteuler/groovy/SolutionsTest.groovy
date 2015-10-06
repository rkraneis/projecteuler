/* Copyright (C) 2015 René Kraneis */
package de.rkraneis.projecteuler.groovy

import org.junit.Test
import static de.rkraneis.projecteuler.groovy.Solutions.*
import static org.junit.Assert.*
import static org.hamcrest.CoreMatchers.*

/**
 *
 * @author RNK
 */
class SolutionsTest {

    @Test public void testBaseline () {
        assertThat baseline(), is(42)
    }
}
