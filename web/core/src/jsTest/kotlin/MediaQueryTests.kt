/*
 * Copyright 2020-2021 JetBrains s.r.o. and respective authors and developers.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE.txt file.
 */

package org.jetbrains.compose.web.core.tests

import org.jetbrains.compose.web.css.CSSMediaQuery
import org.jetbrains.compose.web.css.CSSMediaRuleDeclaration
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.and
import org.jetbrains.compose.web.css.maxHeight
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.media
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.minWidth
import org.jetbrains.compose.web.css.px
import kotlin.test.Test
import kotlin.test.assertEquals

class MediaQueryTests {

    private object CombinedMediaQueries : StyleSheet() {
        val combine by style {
            media(minWidth(200.px).and(maxWidth(400.px))) {
            }

            media(minWidth(300.px), maxWidth(500.px)) {
            }
        }
    }

    private object MediaFeatures : StyleSheet() {
        val features by style {
            media(minWidth(200.px)) {}
            media(minHeight(300.px)) {}
            media(maxWidth(500.px)) {}
            media(maxHeight(600.px)) {}
        }
    }

    @Test
    fun mediaFeatures() {
        assertEquals(
            "@media (min-width: 200px)",
            (MediaFeatures.cssRules[1] as CSSMediaRuleDeclaration).header
        )

        assertEquals(
            "@media (min-height: 300px)",
            (MediaFeatures.cssRules[2] as CSSMediaRuleDeclaration).header
        )

        assertEquals(
            "@media (max-width: 500px)",
            (MediaFeatures.cssRules[3] as CSSMediaRuleDeclaration).header
        )

        assertEquals(
            "@media (max-height: 600px)",
            (MediaFeatures.cssRules[4] as CSSMediaRuleDeclaration).header
        )
    }


    @Test
    fun combineMediaQueries() {
        assertEquals(
            "@media (min-width: 200px) and (max-width: 400px)",
            (CombinedMediaQueries.cssRules[1] as CSSMediaRuleDeclaration).header
        )

        assertEquals(
            "@media (min-width: 300px), (max-width: 500px)",
            (CombinedMediaQueries.cssRules[2] as CSSMediaRuleDeclaration).header
        )
    }

}