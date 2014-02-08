package models;

public interface Serializable <E> {

    public String serialize();

    // Java 8 can do static interface methods, but will give Java 7 users 
    // problems if it's tried. Don't.
    public E loadObject(String serial);
}
