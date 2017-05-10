package po.lab.polynomial;

import java.util.Iterator;
import java.util.LinkedList;

public class SummationIterator implements Iterator<Monomial> {
    Iterator<Monomial> iter1;
    Monomial mono1 = null;
    Iterator<Monomial> iter2;
    Monomial mono2 = null;

    SummationIterator(LinkedList<Monomial> firstCoefficients, LinkedList<Monomial> secondCoefficients) {
        iter1 = firstCoefficients.iterator();
        iter2 = secondCoefficients.iterator();
    }

    private void feed() {
        if (iter1.hasNext() && mono1 == null) {
            mono1 = (Monomial) iter1.next();
        }
        if (iter2.hasNext() && mono2 == null) {
            mono2 = (Monomial) iter2.next();
        }
    }

    @Override
    public boolean hasNext() {
        return iter1.hasNext() || mono1 != null || iter2.hasNext() || mono2 != null;
    }

    @Override
    public Monomial next() {
        feed();
        assert mono1 != null || mono2 != null;
        Monomial result;

        if (mono1 == null || (mono2 != null && mono1.exponent() > mono2.exponent())) {
            result = mono2;
            mono2 = null;
        } else if (mono2 == null || (mono1 != null && mono1.exponent() < mono2.exponent())) {
            result = mono1;
            mono1 = null;
        } else {
            result = mono1.add(mono2);
            mono2 = null;
            mono1 = null;
        }

        return  result;
    }
}
