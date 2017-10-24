package spring.rest.example.validator;

public interface Validator<T> {

    void validate(T data);
}
