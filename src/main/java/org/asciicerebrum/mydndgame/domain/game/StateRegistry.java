package org.asciicerebrum.mydndgame.domain.game;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
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

    public enum StateParticle {

        /**
         * Defines the item currently in used.
         */
        ACTIVE_ITEM,
        /**
         * Defines the per-weapon attack mode. The attack mode can be different
         * for each weapon.
         */
        WEAPON_ATTACK_MODE,
        /**
         * Defines the per-weapon damage type.
         */
        WEAPON_DAMAGE_TYPE
    }

    public enum StateValueType {

        BOOLEAN {
                    @Override
                    public Object parseToType(final String input,
                            final Campaign campaign,
                            final ApplicationContext context) {
                        return Boolean.valueOf(input);
                    }
                },
        STRING {
                    @Override
                    public Object parseToType(final String input,
                            final Campaign campaign,
                            final ApplicationContext context) {
                        return input;
                    }
                },
        LONG {
                    @Override
                    public Object parseToType(final String input,
                            final Campaign campaign,
                            final ApplicationContext context) {
                        return Long.valueOf(input);
                    }
                },
        UNIQUE_ENTITY {
                    @Override
                    public Object parseToType(final String input,
                            final Campaign campaign,
                            final ApplicationContext context) {
                        return campaign.getEntityById(new UniqueId(input));
                    }
                },
        BEAN {
                    @Override
                    public Object parseToType(final String input,
                            final Campaign campaign,
                            final ApplicationContext context) {
                        return context.getBean(input);
                    }
                };

        public abstract Object parseToType(final String input,
                final Campaign campaign, final ApplicationContext context);
    }

    public static class StateRegistryKey {

        private final StateParticle stateParticle;

        private final UniqueEntity contextObject;

        private StateRegistryKey(final StateParticle key,
                final UniqueEntity contextObjectInput) {
            this.stateParticle = key;
            this.contextObject = contextObjectInput;
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
                    .append(this.contextObject, oKey.contextObject)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 31)
                    .append(this.stateParticle)
                    .append(this.contextObject)
                    .toHashCode();
        }
    }

    private final Map<StateRegistryKey, Object> stateMap
            = new HashMap<StateRegistryKey, Object>();

    public void putState(final StateParticle particle,
            final Object stateValue) {
        this.putState(particle, null, stateValue);
    }

    public void putState(final StateParticle particle,
            final UniqueEntity contextObject, final Object stateValue) {
        final StateRegistryKey key
                = new StateRegistryKey(particle, contextObject);
        this.stateMap.put(key, stateValue);
    }

    /**
     * Trying to cast to T.
     *
     * @param <T>
     * @param particle
     * @param contextObject
     * @return
     */
    public <T> T getState(final StateParticle particle,
            final UniqueEntity contextObject) {
        final StateRegistryKey key
                = new StateRegistryKey(particle, contextObject);
        Object stateKey = this.stateMap.get(key);
        if (stateKey != null) {
            return (T) stateKey;
        }
        return null;
    }

    public void removeState(final StateRegistryKey key) {
        this.stateMap.remove(key);
    }

    /**
     * Used for the serialization of the state registry entries.
     *
     * @param particle the particle component of the registry key.
     * @param contextObject the contextual object component of the registry key.
     * @return the type of the state value.
     */
    public StateValueType getStateValueTypeForKey(final StateParticle particle,
            final UniqueEntity contextObject) {
        final StateRegistryKey key
                = new StateRegistryKey(particle, contextObject);
        final Object value = this.stateMap.get(key);

        try {
            return StateValueType.valueOf(
                    value.getClass().getSimpleName().toUpperCase());
        } catch (final IllegalArgumentException iae) {
            //TODO log info
            if (value instanceof UniqueEntity) {
                return StateValueType.UNIQUE_ENTITY;
            }
        }
        // it is not possible to determine whether an object is a bean or not.
        // So the method of elimination is used.
        return StateValueType.BEAN;
    }

    public StateValueType getStateValueTypeForKey(
            final StateParticle particle) {
        return this.getStateValueTypeForKey(particle, null);
    }

}
