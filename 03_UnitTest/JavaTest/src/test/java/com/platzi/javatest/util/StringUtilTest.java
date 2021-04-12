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



}