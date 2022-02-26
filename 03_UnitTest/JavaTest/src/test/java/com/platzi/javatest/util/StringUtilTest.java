package com.platzi.javatest.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void repeat_string_once(){
        String result = StringUtil.repeat("hello", 1);
        assertEquals("hello", result);
    }

    @Test
    public void repeat_string_multiple_times(){
        String result = StringUtil.repeat("hello", 3);
        assertEquals("hellohellohello", result);
    }

    @Test
    public void repeat_string_zero_times(){
        String result = StringUtil.repeat("hello", 0);
        assertEquals("", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void repeat_string_negative_times(){
        StringUtil.repeat("hello", -1);
    }

    @Test
    public void string_is_not_empty(){
        assertFalse(StringUtil.isEmpty("hello"));
    }

    @Test
    public void string_empty_is_empty(){
        assertTrue(StringUtil.isEmpty(""));
    }

    @Test
    public void string_null_is_empty(){
        assertTrue(StringUtil.isEmpty(null));
    }

    @Test
    public void string_with_whitespace_is_empty(){
        assertTrue(StringUtil.isEmpty(" "));
    }


}