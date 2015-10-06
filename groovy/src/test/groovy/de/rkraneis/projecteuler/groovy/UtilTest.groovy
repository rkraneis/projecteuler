/* Copyright (C) 2015 René Kraneis */
package de.rkraneis.projecteuler.groovy

import org.junit.Test
import static de.rkraneis.projecteuler.groovy.Util.*
import static org.junit.Assert.*
import static org.hamcrest.CoreMatchers.*

/**
 *
 * @author RNK
 */
class UtilTest {

    @Test public void testBaseline() { assertThat baseline(), is(42) }
    @Test public void testBaseline42() { assertThat baseline(42), is(42) }
}
