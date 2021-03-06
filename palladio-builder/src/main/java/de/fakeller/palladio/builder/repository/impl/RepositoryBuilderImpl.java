package de.fakeller.palladio.builder.repository.impl;

import de.fakeller.palladio.builder.repository.ComponentBuilder;
import de.fakeller.palladio.builder.repository.InterfaceBuilder;
import de.fakeller.palladio.builder.repository.RepositoryBuilder;
import de.fakeller.palladio.builder.repository.failure.SoftwareInducedFailureBuilder;
import de.fakeller.palladio.builder.repository.failure.impl.SoftwareInducedFailureBuilderImpl;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;

import java.util.ArrayList;
import java.util.List;


public class RepositoryBuilderImpl implements RepositoryBuilder {

    private final Repository eRepository;

    private final List<ComponentBuilder> components = new ArrayList<>();
    private final List<InterfaceBuilder> interfaces = new ArrayList<>();

    public RepositoryBuilderImpl() {
        this.eRepository = RepositoryFactory.eINSTANCE.createRepository();
    }

    @Override
    public ComponentBuilder withComponent(final String name) {
        // create builder
        final ComponentBuilder builder = new ComponentBuilderImpl(this).withEntityName(name);
        this.components.add(builder);

        // link model
        this.eRepository.getComponents__Repository().add(builder.getReference());

        return builder;
    }

    @Override
    public InterfaceBuilder withInterface(final String name) {
        // create builder
        final InterfaceBuilder builder = new InterfaceBuilderImpl(this).withEntityName(name);
        this.interfaces.add(builder);

        // link model
        this.eRepository.getInterfaces__Repository().add(builder.getReference());

        return builder;
    }

    @Override
    public SoftwareInducedFailureBuilder withSoftwareInducedFailure(final String name) {
        return new SoftwareInducedFailureBuilderImpl(this);
    }

    @Override
    public Repository getReference() {
        return this.eRepository;
    }
}
