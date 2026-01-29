package top.blogapi.exception.business_exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import top.blogapi.exception.BaseException;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusinessException extends BaseException {
    protected BusinessException(Builder<?> builder) {
        super(builder);
    }

    public static BusinessExceptionBuilder builder(){
        return new BusinessExceptionBuilder();
    }
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class BusinessExceptionBuilder extends Builder<BusinessExceptionBuilder>{

        public BusinessExceptionBuilder notFound(String errorCode){
            return this
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .errorCode(errorCode.concat("_NOT_FOUND"));
        }

        public BusinessExceptionBuilder validate(){
            return this.errorCode("PARAMETERS_INVALID");
        }

        @Override
        protected BusinessExceptionBuilder self() {
            return this;
        }

        @Override
        public BusinessException build() {
            if(this.httpStatus == HttpStatus.INTERNAL_SERVER_ERROR)
                this.httpStatus = HttpStatus.BAD_REQUEST;
            return new BusinessException(this);
        }
    }
}
