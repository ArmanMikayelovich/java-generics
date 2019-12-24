package com.energizeglobal.internship;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //Diamond
        List<? super List> list = new ArrayList<>();
        // Heap pollution
        list.add(new ArrayList<String>());
        list.add(new ArrayList<Number>());
        ((List) list.get(0)).add(5);
        System.out.println(list.toString());
        System.out.println( ((List) list.get(0)).get(0).getClass());//we added Integer to ArrayList<String>

        Box<String> stringBox = new Box<>("asd");
        Box<String> questionBox = new Box<>("1");
        Comparator<Box<? extends String>> comparator = new BoxComparator<>();
        System.out.println(stringBox.compareHashCodes(questionBox, comparator));

        AutoBoxer autoboxer = (x) -> x;

        System.out.println(autoboxer.box(2.2).getClass());
        System.out.println(autoboxer.box((long) Integer.MAX_VALUE + 2).getClass());
        System.out.println(autoboxer.box((short)1).getClass());

    }



    static class Box<T> {
        private T value;

        Box(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        int compareHashCodes(Box<? extends T> other, Comparator<Box<? extends T>> comparator) {
            return comparator.compare(this, other);
        }

    }


    static class BoxComparator<T> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            return Integer.compare(o1.hashCode(), o2.hashCode());
        }
    }


}
