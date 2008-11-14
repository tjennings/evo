package com.evo;

import com.evo.functions.Subtract;
import com.evo.functions.Add;
import com.evo.functions.Divide;
import com.evo.functions.Multiply;
import junit.framework.TestCase;

public class BasicFunctionTest extends TestCase{
    public void testAdd() {
        Add a = new Add();
        Node n = a.statement(new Constant(1), new Constant(2));
        assertEquals(3.0f, n.result());
        assertEquals("(1.0 + 2.0)", n.toString());
    }
    
    public void testSubtract() {
        Subtract s = new Subtract();
        Node n = s.statement(new Constant(1), new Constant(1));
        
        assertEquals(0.0f, n.result());
        assertEquals("(1.0 - 1.0)", n.toString());
    }
    
    public void testMultiply() {
        Multiply m = new Multiply();
        Node n = m.statement(new Constant(2), new Constant(3));
        
        assertEquals(6.0f, n.result());
        assertEquals("(2.0 * 3.0)", n.toString());
    }
    
    public void testDivide() {
        Divide d = new Divide();
        Node n = d.statement(new Constant(10), new Constant(2));
        
        assertEquals(5.0f, n.result());
        assertEquals("(10.0 / 2.0)", n.toString());
    }
    
    public void testDivideByZeroReturnsNumerator() {
        Divide d = new Divide();
        Node n = d.statement(new Constant(5), new Constant(0));
        
        assertEquals(5.0f, n.result());
        assertEquals("(5.0 / 0.0)", n.toString());
    }
}
