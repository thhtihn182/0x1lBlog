package top.blogapi.exception.business_exception.domain_exception;

import org.springframework.http.HttpStatus;
import top.blogapi.exception.business_exception.DomainException;

public class CommentServiceException extends DomainException {
    public CommentServiceException(CommentServiceExceptionBuilder builder) {
        super(builder);
    }
    public static CommentServiceExceptionBuilder builder() {
        return new CommentServiceExceptionBuilder();
    }

    public static class CommentServiceExceptionBuilder extends DomainExceptionBuilder<CommentServiceExceptionBuilder> {
        public CommentServiceExceptionBuilder() {
            super.domain("COMMENT");
        }

        public CommentServiceExceptionBuilder dataRetrievalFailed(String msg, Throwable e , String ...operations) {
            return this.errorCode("COMMENT_DATA_RETRIVAL_FAILED")
                    .message(msg)
                    .msgCause(e.getMessage())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .cause(e)
                    .messageKey("comment.data_retrival_failed",operations);
        }

        @Override
        protected CommentServiceExceptionBuilder self() {
            return null;
        }

        @Override
        public CommentServiceException build() {
            return new CommentServiceException(this);
        }
    }
}
