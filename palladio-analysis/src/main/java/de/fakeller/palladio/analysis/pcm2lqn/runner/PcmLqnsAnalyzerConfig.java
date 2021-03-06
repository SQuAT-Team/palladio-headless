package de.fakeller.palladio.analysis.pcm2lqn.runner;

import de.fakeller.palladio.config.PcmModelConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Config parameters to locate PCM models on the file system.
 */
public class PcmLqnsAnalyzerConfig extends PcmModelConfig {

    private static final String PROPERTY_OUTPUT_PATH = "Output_Path";

    public PcmLqnsAnalyzerConfig() {
        super();
    }

    public PcmLqnsAnalyzerConfig(final Properties config) {
        super(config);
    }

    /**
     * Cretes a default configuration for the {@link PcmLqnsAnalyzer}, suitable in most use cases.
     */
    public static PcmLqnsAnalyzerConfig defaultConfig() {
        final PcmLqnsAnalyzerConfig config = new PcmLqnsAnalyzerConfig();

        // default model names
        config.setAllocationModel("default.allocation");
        config.setUsageModel("default.usagemodel");

        // default output path
        final Path output;
        try {
            output = Files.createTempDirectory("lqns-analysis");
        } catch (final IOException e) {
            throw new RuntimeException("Cannot create temporary directory for LQNS results", e);
        }
        config.setOutputPath(output.toString());

        return config;
    }

    public String getOutputPath() {
        return this.getPropertyNotNull(PROPERTY_OUTPUT_PATH);
    }

    public void setOutputPath(final String outputPath) {
        this.setProperty(PROPERTY_OUTPUT_PATH, outputPath);
    }
}
