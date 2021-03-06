package de.fakeller.palladio.builder.usage;

import de.fakeller.palladio.builder.EntityHierarchicalBuilder;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;

/**
 * Used to build {@link EntryLevelSystemCall}s.
 */
public interface EntryLevelSystemCallBuilder extends EntityHierarchicalBuilder<EntryLevelSystemCallBuilder, EntryLevelSystemCall, BehaviourBuilder> {

    EntryLevelSystemCallBuilder withPriority(int priority);

    EntryLevelSystemCallBuilder withInputVariableUsage(String name, String specification);

    EntryLevelSystemCallBuilder withOutputVariableUsage(String name, String specification);
}
