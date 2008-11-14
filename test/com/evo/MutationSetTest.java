package com.evo;

import com.evo.*;
import junit.framework.TestCase;

public class MutationSetTest extends TestCase {

    public void tearDown() {
        Randomizer.override(-1);
    }

    public void testOneMutation() {
        MutationSet mutSet = new MutationSet();
        CrossoverMutation mutation = new CrossoverMutation();
        mutSet.add(100f, mutation);

        assertSame(mutation, mutSet.selectMutation());
    }

    public void testSelectMutationThrowsIllegalStateExceptionWhenSetIsEmpty() {
        MutationSet mutSet = new MutationSet();

        try {
            mutSet.selectMutation();
            fail("Should have thrown an exception");
        } catch (IllegalStateException ise) {
            //Test was successful
        }
    }

    public void testExceptionThrownIfNoMutationFound() {
        MutationSet mutSet = new MutationSet();
        CrossoverMutation commonMutation = new CrossoverMutation();

        mutSet.add(90f, commonMutation);

        try {
            Randomizer.override(0.95f);
            mutSet.selectMutation();
            fail("Should have thrown an illegal state exception");
        } catch (IllegalStateException ise) {
            //success
        }
    }

    public void testExceptionThrownIfTotalMutChanceGT100() {
        MutationSet mutSet = new MutationSet();
        CrossoverMutation commonMutation = new CrossoverMutation();

        mutSet.add(90f, commonMutation);
        mutSet.add(11f, commonMutation);

        try {
            mutSet.selectMutation();
            fail("Should have thrown an illegal state exception");
        } catch (IllegalStateException ise) {
            //success
        }
    }

    public void testSelectMutationObeysMutationChance() {
        MutationSet mutSet = new MutationSet();
        CrossoverMutation commonMutation = new CrossoverMutation();
        CrossoverMutation rareMutation = new CrossoverMutation();

        mutSet.add(90f, commonMutation);
        mutSet.add(10f, rareMutation);

        assertFrequency(10.0f, rareMutation, mutSet);
        assertFrequency(90.0f, commonMutation, mutSet);
    }

    public void testThreeMutationChainHasCorrectFrequency() {
        MutationSet mutSet = new MutationSet();
        CrossoverMutation defaultMut = new CrossoverMutation();
        CrossoverMutation rareMutation = new CrossoverMutation();
        CrossoverMutation somewhatCommonMutation = new CrossoverMutation();

        mutSet.add(20f, rareMutation);
        mutSet.add(62f, somewhatCommonMutation);
        mutSet.add(18f, defaultMut);

        assertFrequency(20.0f, rareMutation, mutSet);
        assertFrequency(62.0f, somewhatCommonMutation, mutSet);
        assertFrequency(18.0f, defaultMut, mutSet);
    }

    private void assertFrequency(float expectedFrequency, Mutation rareMutation, MutationSet mutSet) {
        float hit = 0;
        float sampleSize = 100000;
        for(int i = 0; i < sampleSize; i++ ) {
            if(rareMutation == mutSet.selectMutation()) hit++;
        }
        assertEquals(expectedFrequency, (hit / sampleSize * 100), 0.5);
    }
}
