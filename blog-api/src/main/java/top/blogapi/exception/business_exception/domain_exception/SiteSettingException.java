package top.blogapi.exception.business_exception.domain_exception;

import org.springframework.http.HttpStatus;
import top.blogapi.exception.business_exception.DomainException;

import java.util.List;
import java.util.Map;

public class SiteSettingException extends DomainException {
    public SiteSettingException(SiteSettingExceptionBuilder builder) {
        super(builder);
    }
    public static SiteSettingExceptionBuilder builder(){
        return new SiteSettingExceptionBuilder();
    }
    public static class SiteSettingExceptionBuilder extends DomainExceptionBuilder<SiteSettingExceptionBuilder> {
        public SiteSettingExceptionBuilder() {
            super.domain("SITE_SETTING");
        }

        public SiteSettingExceptionBuilder operationSiteSettingUnsuccessful(String operateOfErrorCode, String msg,
                                                                            HttpStatus httpStatus, String ...operations) {
            Map<String, String> operate =
                    Map.of("select","SELECT_SITE_SETTING_FAILURE",
                            "insert","INSERT_SITE_SETTING_FAILURE",
                            "update","UPDATE_SITE_SETTING_FAILURE",
                            "delete","DELETE_SITE_SETTING_FAILURE");
            return this.errorCode(operate.get(operateOfErrorCode))
                    .message(msg)
                    .httpStatus(httpStatus)
                    .messageKey("site_setting.operation_unsuccessful",operations);
        }

        public SiteSettingExceptionBuilder dataRetrievalFailed(String msg, Throwable cause, String... operations) {
            return this.errorCode("SITE_SETTING_DATA_RETRIVAL_FAILED")
                    .message(msg)
                    .cause(cause)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .messageKey("site_setting.data_retrieval_failed",operations);
        }

        @Override
        protected SiteSettingExceptionBuilder self() {
            return this;
        }

        @Override
        public SiteSettingException build() {
            return new SiteSettingException(self());
        }
    }
}
