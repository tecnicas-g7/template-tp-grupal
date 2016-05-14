package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.server.BuilderLoader;

public class Main {
    public static void main(String[] args) throws Exception {
        GameBuilder builder = BuilderLoader.load(args[0]);
        builder.build();
    }
}
