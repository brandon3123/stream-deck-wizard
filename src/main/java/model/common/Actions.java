package model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import enums.name.action.ActionName;
import model.action.Action;

/**
 * Stream Deck uses an upper camel case strategy.
 */
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Actions {

    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_0_0)
    private final Action action0_0;
    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_0_1)
    private final Action action0_1;
    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_0_2)
    private final Action action0_2;

    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_1_0)
    private final Action action1_0;
    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_1_1)
    private final Action action1_1;
    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_1_2)
    private final Action action1_2;

    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_2_0)
    private final Action action2_0;
    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_2_1)
    private final Action action2_1;
    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_2_2)
    private final Action action2_2;

    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_3_0)
    private final Action action3_0;
    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_3_1)
    private final Action action3_1;
    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_3_2)
    private final Action action3_2;

    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_4_0)
    private final Action action4_0;
    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_4_1)
    private final Action action4_1;
    @JsonProperty(value = ActionName.ActionNameConstant.ACTION_4_2)
    private final Action action4_2;

    public Action getAction0_0() {
        return action0_0;
    }

    public Action getAction0_1() {
        return action0_1;
    }

    public Action getAction0_2() {
        return action0_2;
    }

    public Action getAction1_0() {
        return action1_0;
    }

    public Action getAction1_1() {
        return action1_1;
    }

    public Action getAction1_2() {
        return action1_2;
    }

    public Action getAction2_0() {
        return action2_0;
    }

    public Action getAction2_1() {
        return action2_1;
    }

    public Action getAction2_2() {
        return action2_2;
    }

    public Action getAction3_0() {
        return action3_0;
    }

    public Action getAction3_1() {
        return action3_1;
    }

    public Action getAction3_2() {
        return action3_2;
    }

    public Action getAction4_0() {
        return action4_0;
    }

    public Action getAction4_1() {
        return action4_1;
    }

    public Action getAction4_2() {
        return action4_2;
    }

    private Actions(Builder builder) {
        this.action0_0 = builder.action0_0;
        this.action0_1 = builder.action0_1;
        this.action0_2 = builder.action0_2;

        this.action1_0 = builder.action1_0;
        this.action1_1 = builder.action1_1;
        this.action1_2 = builder.action1_2;

        this.action2_0 = builder.action2_0;
        this.action2_1 = builder.action2_1;
        this.action2_2 = builder.action2_2;

        this.action3_0 = builder.action3_0;
        this.action3_1 = builder.action3_1;
        this.action3_2 = builder.action3_2;

        this.action4_0 = builder.action4_0;
        this.action4_1 = builder.action4_1;
        this.action4_2 = builder.action4_2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Action action0_0;
        private Action action0_1;
        private Action action0_2;

        private Action action1_0;
        private Action action1_1;
        private Action action1_2;

        private Action action2_0;
        private Action action2_1;
        private Action action2_2;

        private Action action3_0;
        private Action action3_1;
        private Action action3_2;

        private Action action4_0;
        private Action action4_1;
        private Action action4_2;
        
        public Actions build() {
            return new Actions(this);
        }

        public Builder action0_0(Action action0_0) {
            this.action0_0 = action0_0;
            return this;
        }

        public Builder action0_1(Action action01) {
            this.action0_1 = action01;
            return this;
        }

        public Builder action0_2(Action action02) {
           this.action0_2 = action02;
           return this;
        }

        public Builder action1_0(Action action1_0) {
            this.action1_0 = action1_0;
            return this;
        }

        public Builder action1_1(Action action1_1) {
            this.action1_1 = action1_1;
            return this;
        }

        public Builder action1_2(Action action1_2) {
            this.action1_2 = action1_2;
            return this;
        }

        public Builder action2_0(Action action2_0) {
            this.action2_0 = action2_0;
            return this;
        }

        public Builder action2_1(Action action2_1) {
            this.action2_1 = action2_1;
            return this;
        }

        public Builder action2_2(Action action2_2) {
            this.action2_2 = action2_2;
            return this;
        }

        public Builder action3_0(Action action3_0) {
            this.action3_0 = action3_0;
            return this;
        }

        public Builder action3_1(Action action3_1) {
            this.action3_1 = action3_1;
            return this;
        }

        public Builder action3_2(Action action3_2) {
            this.action3_2 = action3_2;
            return this;
        }

        public Builder action4_0(Action action4_0) {
            this.action4_0 = action4_0;
            return this;
        }

        public Builder action4_1(Action action4_1) {
            this.action4_1 = action4_1;
            return this;
        }

        public Builder action4_2(Action action4_2) {
            this.action4_2 = action4_2;
            return this;
        }

        public Builder nextSpot(Action action) {
            if (action0_0 == null) {
                return action0_0(action);
            }

            if (action0_1 == null) {
                return action0_1(action);
            }

            if (action0_2 == null) {
                return action0_2(action);
            }

            if (action1_0 == null) {
                return action1_0(action);
            }

            if (action1_1 == null) {
                return action1_1(action);
            }

            if (action1_2 == null) {
                return action1_2(action);
            }

            if (action2_0 == null) {
                return action2_0(action);
            }

            if (action2_1 == null) {
                return action2_1(action);
            }

            if (action2_2 == null) {
                return action2_2(action);
            }

            if (action3_0 == null) {
                return action3_0(action);
            }

            if (action3_1 == null) {
                return action3_1(action);
            }

            if (action3_2 == null) {
                return action3_2(action);
            }

            if (action4_0 == null) {
                return action4_0(action);
            }

            if (action4_1 == null) {
                return action4_1(action);
            }

            return action4_2(action);
        }
    }
}