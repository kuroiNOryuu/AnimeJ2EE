package ch.hevs.exception;

public class AnimeException extends RuntimeException {

	public AnimeException() {
		super();
	}

	public AnimeException(String arg0) {
		super(arg0);
	}

	public AnimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AnimeException(Throwable arg0) {
		super(arg0);
	}

}
