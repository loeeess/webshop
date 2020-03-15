package nl.hsleiden;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.bundles.assets.AssetsBundleConfiguration;
import io.dropwizard.bundles.assets.AssetsConfiguration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Peter van Vliet
 */
public class ApiConfiguration extends Configuration implements AssetsBundleConfiguration {
    @Valid
    @NotNull
    @JsonProperty
    private final DataSourceFactory database = new DataSourceFactory();
    @Valid
    @NotNull
    @JsonProperty
    private final AssetsConfiguration assets = new AssetsConfiguration();
    @NotEmpty
    @JsonProperty
    private String apiName;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    @Override
    public AssetsConfiguration getAssetsConfiguration() {
        return assets;
    }

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
