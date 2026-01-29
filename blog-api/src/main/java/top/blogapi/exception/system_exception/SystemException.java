package top.blogapi.exception.system_exception;

import top.blogapi.exception.BaseException;

public class SystemException extends BaseException {
    protected SystemException(Builder<?> builder) {
        super(builder);
    }

    public static SystemExceptionBuilder builder(){
        return new SystemExceptionBuilder();
    }

    public static class SystemExceptionBuilder extends Builder<SystemExceptionBuilder> {

        public SystemExceptionBuilder persistence(){
            return this.errorCode("PERSISTENCE_ERROR");

        }

        public SystemExceptionBuilder operate(String operate){
            return this.context("operate",operate);
        }

        @Override
        protected SystemExceptionBuilder self() {
            return this;
        }

        @Override
        public SystemException build() {
            return new SystemException(this);
        }
    }

}
