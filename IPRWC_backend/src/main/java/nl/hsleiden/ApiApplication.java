package nl.hsleiden;

import com.google.inject.Module;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.hubspot.dropwizard.guice.GuiceBundle.Builder;
import io.dropwizard.Application;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.hsleiden.model.UserModel;
import nl.hsleiden.service.AuthService;
import nl.hsleiden.service.UserService;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * @author Peter van Vliet
 */
public class ApiApplication extends Application<ApiConfiguration> {
    private static final Logger MAINLOGGER = LoggerFactory.getLogger(ApiApplication.class);
    private final Logger logger = LoggerFactory.getLogger(ApiApplication.class);
    private GuiceBundle guiceBundle;
    private ApiGuiceModule apiGuiceModule;
    private String name;

    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[]{"server", "configuration.yml"};
        }

        try {
            new ApiApplication().run(args);
        } catch (Exception e) {
            MAINLOGGER.error(Arrays.toString(e.getStackTrace()));
        }

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootstrap) {
        ConfiguredBundle assetsBundle = new ConfiguredAssetsBundle("/assets/", "/client", "index.html");

        apiGuiceModule = new ApiGuiceModule(bootstrap);
        guiceBundle = createGuiceBundle(ApiConfiguration.class, apiGuiceModule);

        bootstrap.addBundle(assetsBundle);
        bootstrap.addBundle(guiceBundle);

        bootstrap.addBundle(new MultiPartBundle());
    }

    @Override
    public void run(ApiConfiguration configuration, Environment environment) {
        name = configuration.getApiName();

        logger.info("Set API name to {}", name);

        setupAuthentication(environment);
        configureClientFilter(environment);
        enableCorsHeaders(environment);
    }

    private void enableCorsHeaders(Environment environment) {
	final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

    	cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    	cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
    	cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
    	cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER, "true");
    	cors.setInitParameter("allowCredentials", "true");
    	cors.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
	cors.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, Boolean.FALSE.toString());
    }

    private GuiceBundle createGuiceBundle(Class<ApiConfiguration> configurationClass, Module module) {
        Builder guiceBuilder = GuiceBundle.<ApiConfiguration>newBuilder()
                .addModule(module)
                .enableAutoConfig("nl.hsleiden")
                .setConfigClass(configurationClass);

        return guiceBuilder.build();
    }

    private void setupAuthentication(Environment environment) {
        UserService service = guiceBundle.getInjector().getInstance(UserService.class);
        ApiUnauthorizedHandler unauthorizedHandler = guiceBundle.getInjector().getInstance(ApiUnauthorizedHandler.class);

        // @NOTE:
        // This creates the authenticator,
        // which requires we pass in *ALL* attributes of the authenticator.
        AuthService authenticator = new UnitOfWorkAwareProxyFactory(apiGuiceModule.getHibernateBundle())
                .create(AuthService.class, UserService.class, service);

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<UserModel>()
                        .setAuthenticator(authenticator)
                        .setAuthorizer(authenticator)
                        .setRealm("SUPER SECRET STUFF")
                        .setUnauthorizedHandler(unauthorizedHandler)
                        .buildAuthFilter()));

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(UserModel.class));
    }

    private void configureClientFilter(Environment environment) {
        environment.getApplicationContext().addFilter(
                new FilterHolder(new ClientFilter()),
                "/*",
                EnumSet.allOf(DispatcherType.class)
        );
    }
}
