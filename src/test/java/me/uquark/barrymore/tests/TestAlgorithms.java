package me.uquark.barrymore.tests;

import me.uquark.barrymore.algorithm.Algorithm;
import org.junit.Assert;
import org.junit.Test;

public class TestAlgorithms {
    @Test
    public void testLibLCS() {
        Assert.assertEquals("Invalid longest common substring", Algorithm.longestCommonSubstring("абакаба", "каброн"), "каб");
        Assert.assertEquals("Invalid longest common substring", Algorithm.longestCommonSubstring("абакаба", "рон уизли"), "");
    }
}
