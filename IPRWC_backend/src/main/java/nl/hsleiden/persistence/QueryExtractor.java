package nl.hsleiden.persistence;

import org.hibernate.query.Query;

/**
 * A simple Interface to extract type `R` from a query over type `T`.
 *
 * @param <T>
 * @param <R>
 * @author Loes
 */
public interface QueryExtractor<T, R> {
    R extract(Query<T> query);
}
