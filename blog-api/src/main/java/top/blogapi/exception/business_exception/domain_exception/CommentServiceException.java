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

        public CommentServiceExceptionBuilder operationCommentUnsuccessful(String domain,String errorCode, String msg,
                                                                           HttpStatus httpStatus  , String ...operations) {
            return this.domain(domain)
                    .errorCode(errorCode)
                    .message(msg)
                    .httpStatus(httpStatus)
                    .messageKey(domain.toLowerCase()+"."+errorCode.toLowerCase(),operations);
        }

        public CommentServiceExceptionBuilder invalidParameters(String id,String msg, String ...operations) {
            return this.errorCode("COMMENT_INVALID_PARAMETERS")
                    .message(msg)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .entityId(id)
                    .messageKey("comment.invalid_parameters",operations);
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
