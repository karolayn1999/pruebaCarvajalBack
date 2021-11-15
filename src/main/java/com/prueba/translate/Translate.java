package com.prueba.translate;

public interface Translate<I,O> {

	I translateTo(final O input);
}
