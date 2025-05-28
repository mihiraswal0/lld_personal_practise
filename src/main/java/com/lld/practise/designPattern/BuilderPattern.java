package com.lld.practise.designPattern;

 class Burger {
    private final String bun;
    private final String patty;
    private final boolean cheese;
    private final boolean lettuce;
    private final boolean tomato;

    // Private constructor: only the Builder can create it
    private Burger(BurgerBuilder builder) {
        this.bun = builder.bun;
        this.patty = builder.patty;
        this.cheese = builder.cheese;
        this.lettuce = builder.lettuce;
        this.tomato = builder.tomato;
    }

    @Override
    public String toString() {
        return "Burger with " + bun + " bun, " + patty + " patty" +
                (cheese ? ", cheese" : "") +
                (lettuce ? ", lettuce" : "") +
                (tomato ? ", tomato" : "");
    }

    // 🔨 Static nested Builder class
    public static class BurgerBuilder {
        private final String bun;
        private final String patty;
        private boolean cheese;
        private boolean lettuce;
        private boolean tomato;

        public BurgerBuilder(String bun, String patty) {
            this.bun = bun;
            this.patty = patty;
        }

        public BurgerBuilder cheese(boolean value) {
            this.cheese = value;
            return this;
        }

        public BurgerBuilder lettuce(boolean value) {
            this.lettuce = value;
            return this;
        }

        public BurgerBuilder tomato(boolean value) {
            this.tomato = value;
            return this;
        }

        public Burger build() {
            return new Burger(this);
        }
    }
}

public class BuilderPattern {
}

