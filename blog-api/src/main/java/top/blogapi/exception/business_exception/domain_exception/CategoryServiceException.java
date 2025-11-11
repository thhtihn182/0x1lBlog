package top.blogapi.exception.business_exception.domain_exception;

import org.springframework.http.HttpStatus;
import top.blogapi.exception.business_exception.DomainException;

public class CategoryServiceException extends DomainException {
    public CategoryServiceException(CategoryServiceExceptionBuilder builder) {
        super(builder);
    }
    public static CategoryServiceExceptionBuilder builder() {
        return new CategoryServiceExceptionBuilder();
    }
    public static class CategoryServiceExceptionBuilder extends DomainExceptionBuilder<CategoryServiceExceptionBuilder> {
        public CategoryServiceExceptionBuilder() {
            super.domain("CATEGORY");
        }

        public CategoryServiceExceptionBuilder categoryNotExist(String domain, String msg, String ...operation) {
            return this.domain(domain)
                    .errorCode("CATEGORY_NOT_EXIST")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(msg)
                    .messageKey(domain.toLowerCase()+".invalid_parameters",new Object[]{operation});

        }

        public CategoryServiceExceptionBuilder blogDoesntAddCategories(String domain, HttpStatus httpStatus, String msg,String ...operation) {
            return this.domain(domain)
                    .errorCode("BLOG_DOES_NOT_ADD_CATEGORIES")
                    .httpStatus(httpStatus)
                    .message(msg)
                    .messageKey(domain.toLowerCase()+".blog_doesnt_add_categories",new Object[]{operation});
        }

        public CategoryServiceExceptionBuilder dataRetrievalFailed(String msg, String ...operation) {
            return this.errorCode("CATEGORY_DATA_RETRIEVAL_FAILED")
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(msg)
                    .messageKey("category.data_retrieval_failed",new Object[]{operation});
        }

        public CategoryServiceExceptionBuilder categoryExistBlogs(String domain, String msg, String ...operation) {
            return this.domain(domain)
                    .errorCode("CATEGORY_EXIST_BLOGS")
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(msg)
                    .messageKey(domain.toLowerCase()+".category_exist_blogs",new Object[]{operation});
        }

        public CategoryServiceExceptionBuilder operationCategoryUnsuccessful(String domain, HttpStatus httpStatus, String msg, String ...operation) {
            return this.domain(domain)
                    .httpStatus(httpStatus)
                    .message(msg)
                    .messageKey(domain.toLowerCase()+".operation_category_unsuccessful",new Object[]{operation});
        }

        @Override
        protected CategoryServiceExceptionBuilder self() {
            return this;
        }

        @Override
        public CategoryServiceException build() {
            return new CategoryServiceException(this);
        }
    }
}
