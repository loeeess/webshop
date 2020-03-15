package nl.hsleiden;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import org.hibernate.SessionFactory;

/**
 * @author Peter van Vliet
 */
public class ApiGuiceModule extends AbstractModule {
    private final HibernateBundle<ApiConfiguration> hibernateBundle;

    public ApiGuiceModule(Bootstrap<ApiConfiguration> bootstrap) {

        hibernateBundle = new ScanningHibernateBundle<ApiConfiguration>("nl.hsleiden.model") {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(ApiConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        };

        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    protected void configure() {
        //Deze moet blijkbaar empty zijn anders juicy code smell
    }

    @Provides
    public SessionFactory provideSessionFactory() {
        return hibernateBundle.getSessionFactory();
    }

    public HibernateBundle<ApiConfiguration> getHibernateBundle() {
        return hibernateBundle;
    }
}
