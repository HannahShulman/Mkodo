package com.hanna.mkodo.test.data.mappers

import com.hanna.mkodo.test.data.responses.DrawResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DrawMapperTest {

    private val response = DrawResponse(
        id = "draw-1",
        drawDate = "2023-05-15",
        number1 = "2",
        number2 = "16",
        number3 = "23",
        number4 = "44",
        number5 = "47",
        number6 = "52",
        bonusBall = "14",
        topPrize = 4000000000
    )

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_id_mapped_as_expected() {
        assertEquals("draw-1", response.map().id)
    }

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_date_mapped_as_expected() {
        assertEquals("15 May, 2023", response.map().drawDate)
    }

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_number1_mapped_as_expected() {
        assertEquals("2", response.map().number1)
    }

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_number2_mapped_as_expected() {
        assertEquals("16", response.map().number2)
    }

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_number3_mapped_as_expected() {
        assertEquals("23", response.map().number3)
    }

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_number4_mapped_as_expected() {
        assertEquals("44", response.map().number4)
    }

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_number5_mapped_as_expected() {
        assertEquals("47", response.map().number5)
    }

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_number6_mapped_as_expected() {
        assertEquals("52", response.map().number6)
    }

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_bonusBall_mapped_as_expected() {
        assertEquals("14", response.map().bonusBall)
    }

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_topPrize_mapped_as_expected() {
        assertEquals(4000000000, response.map().topPrize)
    }
}