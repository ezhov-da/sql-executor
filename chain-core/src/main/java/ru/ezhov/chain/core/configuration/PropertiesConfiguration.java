package ru.ezhov.chain.core.configuration;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesConfiguration implements Configuration {
    private static final String SOURCE = "source.";
    private static final String DATA_SET = "dataset.";
    private static final String CHAIN_LINK = "chainlink.";

    private Properties properties;
    private Set<SourceConfiguration> sourceConfigurations;
    private Set<DataSetConfiguration> dataSetConfigurations;
    private Set<ChainLinkConfiguration> chainLinkConfigurations;

    private String pathToPropertyFile;

    public PropertiesConfiguration(String pathToPropertyFile) throws Exception {
        this.pathToPropertyFile = pathToPropertyFile;

        properties = new Properties();

        properties.load(new FileInputStream(pathToPropertyFile));

        sourceConfigurations = new HashSet<>();
        dataSetConfigurations = new HashSet<>();
        chainLinkConfigurations = new HashSet<>();

        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for (Map.Entry<Object, Object> objectEntry : entries) {
            String key = objectEntry.getKey().toString();
            String value = objectEntry.getValue().toString();

            if (key.startsWith(SOURCE)) {
                sourceConfigurations.add(new SourceConfiguration(key.replace(SOURCE, ""), value));
            } else if (key.startsWith(DATA_SET)) {
                dataSetConfigurations.add(new DataSetConfiguration(key.replace(DATA_SET, ""), value));
            } else if (key.startsWith(CHAIN_LINK)) {
                chainLinkConfigurations.add(new ChainLinkConfiguration(key.replace(CHAIN_LINK, ""), value));
            }
        }
    }

    public Set<SourceConfiguration> getSourceConfiguration() {
        return sourceConfigurations;
    }

    public Set<DataSetConfiguration> getDataSetConfiguration() {
        return dataSetConfigurations;
    }

    public Set<ChainLinkConfiguration> getChainLinkConfiguration() {
        return chainLinkConfigurations;
    }
}