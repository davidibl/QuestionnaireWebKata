package de.lv1871.dms.QuestionnaireWebKata.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.google.common.base.Objects;

public class LambdaExtensions {

	public static <T> Predicate<T> equal(T filterValue) {
		return (value) -> Objects.equal(value, filterValue);
	}

	public static <F, T> Predicate<F> equal(Function<F, T> mapper, T filterValue) {
		return (value) -> Objects.equal(mapper.apply(value), filterValue);
	}

	public static <F, T extends List<R>, R> Function<F, Stream<R>> toStream(Function<F, T> mapper) {
		return (value) -> mapper.apply(value).stream();
	}

	public static <T> Supplier<T> exception(Class<T> exceptionType, String message) {
		return () -> {
			try {
				return exceptionType.getConstructor(String.class).newInstance(message);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				throw new RuntimeException();
			}
		};
	}

}
