package org.asciicerebrum.neocortexengine.domain.game;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.springframework.context.ApplicationContext;

/**
 * State registry settings. <br>
 * E.g.: WEAPON_DAMAGE_TYPE + myDaggerBeanId = piercingDamageBeanId <br>
 * E.g.: POWER_ATTACK_BONUS = 4 <br>
 * e.g.: XYZ = true <br>
 * e.g.: DODGE_OPPONENT = someMonsterBeanId
 *
 * @author species8472
 */
public class StateRegistry {

    /**
     * Some kind of further characterization of the aspect of the state.
     */
    public enum StateParticle {

        /**
         * Defines the item currently in used.
         */
        ACTIVE_ITEM,
        /**
         * The value of the bonus the character can define to be used in the
         * power attack feat.
         */
        POWER_ATTACK_BONUS,
        /**
         * Defines the per-weapon attack mode. The attack mode can be different
         * for each weapon.
         */
        WEAPON_ATTACK_MODE,
        /**
         * Defines the per-weapon damage type.
         */
        WEAPON_DAMAGE_TYPE,
        /**
         * Defines if weapon is used in weapon finesse mode, e.g. rapier.
         */
        WEAPON_FINESSE_MODE
    }

    /**
     * The type of the state value.
     */
    public enum StateValueType {

        /**
         * A boolean type. True of false.
         */
        BOOLEAN {
                    @Override
                    public Object parseToType(final String input,
                            final ApplicationContext context) {
                        return Boolean.valueOf(input);
                    }
                },
        /**
         * An ordinary string type.
         */
        STRING {
                    @Override
                    public Object parseToType(final String input,
                            final ApplicationContext context) {
                        return input;
                    }
                },
        /**
         * A numerical value type of the long variant.
         */
        LONG {
                    @Override
                    public Object parseToType(final String input,
                            final ApplicationContext context) {
                        return Long.valueOf(input);
                    }
                },
        /**
         * A unique entity used.
         */
        UNIQUE_ID {
                    @Override
                    public Object parseToType(final String input,
                            final ApplicationContext context) {
                        return new UniqueId(input);
                    }
                },
        /**
         * A bean from the application context.
         */
        BEAN {
                    @Override
                    public Object parseToType(final String input,
                            final ApplicationContext context) {
                        return context.getBean(input);
                    }
                };

        /**
         * Parses a string input to its corresponding object instance.
         *
         * @param input the input to parse to an object.
         * @param context the application context.
         * @return the parsed object.
         */
        public abstract Object parseToType(final String input,
                final ApplicationContext context);
    }

    /**
     * The key objects used in the state registry.
     */
    public static final class StateRegistryKey {

        /**
         * Initial value for hash code calculation.
         */
        private static final int INITIAL_NON_ZERO_ODD_NUMBER = 17;

        /**
         * Modifier for hash code calculation.
         */
        private static final int MULTIPLIER_NON_ZERO_ODD_NUMBER = 31;

        /**
         * The state particles characterizing the state of the object.
         */
        private final StateParticle stateParticle;

        /**
         * The object whose state is to be described.
         */
        private final UniqueId contextObjectId;

        /**
         * Constructing the state key out of the particle and the unique entity.
         *
         * @param key the particle for a better description of the state.
         * Sometimes the entit itself is not enough because it can have
         * different states for different aspects of it.
         * @param contextObjectIdInput the object to describe the state for.
         */
        private StateRegistryKey(final StateParticle key,
                final UniqueId contextObjectIdInput) {
            this.stateParticle = key;
            this.contextObjectId = contextObjectIdInput;
        }

        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof StateRegistryKey)) {
                return false;
            }
            if (o == this) {
                return true;
            }
            StateRegistryKey oKey = (StateRegistryKey) o;
            return new EqualsBuilder()
                    .append(this.stateParticle, oKey.stateParticle)
                    .append(this.contextObjectId, oKey.contextObjectId)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(INITIAL_NON_ZERO_ODD_NUMBER,
                    MULTIPLIER_NON_ZERO_ODD_NUMBER)
                    .append(this.stateParticle)
                    .append(this.contextObjectId)
                    .toHashCode();
        }
    }

    /**
     * The central map representing the state registry.
     */
    private final Map<StateRegistryKey, Object> stateMap
            = new HashMap<StateRegistryKey, Object>();

    /**
     * Putting a state value with its particle into the registry.
     *
     * @param particle the particle describing the state.
     * @param stateValue the value of the state.
     */
    public final void putState(final StateParticle particle,
            final Object stateValue) {
        this.putState(particle, null, stateValue);
    }

    /**
     * Putting a state value, the affected object and its particle into the
     * registry.
     *
     * @param particle the particle describing the state.
     * @param contextObjectId the unique entity which is affected by the state.
     * @param stateValue the value of the state.
     */
    public final void putState(final StateParticle particle,
            final UniqueId contextObjectId, final Object stateValue) {
        final StateRegistryKey key
                = new StateRegistryKey(particle, contextObjectId);
        this.stateMap.put(key, stateValue);
    }

    /**
     * Retrieves the state back from the registry. Trying to cast to T.
     *
     * @param <T> The type of the object which is to be retrieved.
     * @param particle the particle describing the state.
     * @param contextObjectId the unique entity affected.
     * @return the state value.
     */
    public final <T> T getState(final StateParticle particle,
            final UniqueId contextObjectId) {
        final StateRegistryKey key
                = new StateRegistryKey(particle, contextObjectId);
        Object stateKey = this.stateMap.get(key);
        if (stateKey != null) {
            return (T) stateKey;
        }
        return null;
    }

    /**
     * Deletes an entry from the state registry.
     *
     * @param key the key of the element to remove.
     */
    public final void removeState(final StateRegistryKey key) {
        this.stateMap.remove(key);
    }

    /**
     * Used for the serialization of the state registry entries.
     *
     * @param particle the particle component of the registry key.
     * @param contextObjectId the contextual object component of the registry
     * key.
     * @return the type of the state value.
     */
    public final StateValueType getStateValueTypeForKey(
            final StateParticle particle, final UniqueId contextObjectId) {
        final StateRegistryKey key
                = new StateRegistryKey(particle, contextObjectId);
        final Object value = this.stateMap.get(key);

        if (value instanceof UniqueId) {
            return StateValueType.UNIQUE_ID;
        }

        final String valueClass = value.getClass().getSimpleName()
                .toUpperCase(Locale.ENGLISH);
        if (EnumUtils.isValidEnum(StateValueType.class, valueClass)) {
            return StateValueType.valueOf(valueClass);
        }
        // it is not possible to determine whether an object is a bean or not.
        // So the method of elimination is used.
        return StateValueType.BEAN;
    }

    /**
     * Used for the serialization of the state registry entries.
     *
     * @param particle the particle component of the registry key.
     * @return the type of the state value.
     */
    public final StateValueType getStateValueTypeForKey(
            final StateParticle particle) {
        return this.getStateValueTypeForKey(particle, null);
    }

}
