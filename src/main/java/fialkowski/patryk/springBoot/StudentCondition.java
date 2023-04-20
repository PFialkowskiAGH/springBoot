package fialkowski.patryk.springBoot;

public enum StudentCondition {
    Chory {
        @Override public String toString()
        {
            return "Chory";
        }
    },
    Odrabiający {
        @Override public String toString()
        {
            return "Odrabiający";
        }
    },
    Nieobecny {
        @Override public String toString()
        {
            return "Nieobecny";
        }
    },
    Obecny {
        @Override public String toString() { return "Obecny"; }
    }
}

