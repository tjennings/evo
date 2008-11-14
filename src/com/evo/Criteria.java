package com.evo;

import java.util.List;
import java.util.ArrayList;

public class Criteria {
    private float expected;
    private List<VarSetting> vars = new ArrayList<VarSetting>();

    public Criteria expects(float expected) {
        this.expected = expected;
        return this;
    }

    public float expected() {
        return expected;
    }

    public Criteria var(String varName, float value) {
        vars.add(new VarSetting(varName, value));
        return this;
    }

    public void setVars(Individual indiv) {
        for(VarSetting vs : vars) {
            indiv.setVar(vs.name, vs.value);
        }
    }

    class VarSetting {
        String name;
        float value;

        VarSetting(String name, float value) {
            this.name = name;
            this.value = value;
        }
    }

}










