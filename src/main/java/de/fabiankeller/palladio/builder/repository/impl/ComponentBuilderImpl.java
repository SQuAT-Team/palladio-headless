package de.fabiankeller.palladio.builder.repository.impl;

import de.fabiankeller.palladio.builder.repository.ComponentBuilder;
import de.fabiankeller.palladio.builder.repository.InterfaceBuilder;
import de.fabiankeller.palladio.builder.repository.RepositoryBuilder;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.RepositoryFactory;


public class ComponentBuilderImpl extends AbstractHierarchicalBuilder<ComponentBuilder, BasicComponent, RepositoryBuilder> implements ComponentBuilder {


    ComponentBuilderImpl(final RepositoryBuilder belongsTo) {
        super(belongsTo);
    }

    @Override
    protected BasicComponent newEModel() {
        return RepositoryFactory.eINSTANCE.createBasicComponent();
    }

    @Override
    protected void registerParent() {
        this.eModel.setRepository__RepositoryComponent(this.belongsTo.getReference());
    }


    @Override
    public ComponentBuilder provides(final InterfaceBuilder iface) {
        // create model
        final OperationProvidedRole provided = RepositoryFactory.eINSTANCE.createOperationProvidedRole();
        provided.setEntityName(String.format("%s_provides_%s", this.eModel.getEntityName(), iface.getReference().getEntityName()));
        provided.setProvidedInterface__OperationProvidedRole(iface.getReference());

        // link model
        this.eModel.getProvidedRoles_InterfaceProvidingEntity().add(provided);

        return this;
    }

    @Override
    public ComponentBuilder requires(final InterfaceBuilder iface) {
        // create model
        final OperationRequiredRole required = RepositoryFactory.eINSTANCE.createOperationRequiredRole();
        required.setEntityName(String.format("%s_requires_%s", this.eModel.getEntityName(), iface.getReference().getEntityName()));
        required.setRequiredInterface__OperationRequiredRole(iface.getReference());

        // link model
        this.eModel.getRequiredRoles_InterfaceRequiringEntity().add(required);

        return this;
    }
}
