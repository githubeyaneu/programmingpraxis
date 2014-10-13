package eu.eyan.programmingpraxis;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ModularFactorialTest
{
    @Test
    public void test_ModularFactorial()
    {
        assertThat(ModularFactorial.calculateWithPrimeModulo(1000000L, 1001001779L)).isEqualTo(744950559L);
    }
}
