package nl.hsleiden.persistence;

/**
 * A little interface for the setup of CriteriaQueries!
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */
public interface TriFunction<T, U, V> {
    void apply(T t, U u, V v);
}
