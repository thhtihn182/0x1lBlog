package top.blogapi.exception.business_exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import top.blogapi.exception.BaseException;

@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Getter
public abstract class DomainException extends BaseException {
    String domain;
    String entityId;
    String msgCause;

    public DomainException(DomainExceptionBuilder<?> builder) {
        super(builder);
        this.domain = builder.domain;
        this.entityId = builder.entityId;
        this.msgCause = builder.msgCause;
    }

    @FieldDefaults(level = AccessLevel.PROTECTED)
    public static abstract class DomainExceptionBuilder<T extends DomainExceptionBuilder<T>>
            extends BaseException.Builder<T> {
            String domain;
            String entityId;
            String msgCause;

            public T domain(String domain) {
                this.domain = domain;
                return this.self();
            }
            public T entityId(String entityId) {
                this.entityId = entityId;
                return this.self();
            }

            public T msgCause(String msgCause) {
                this.msgCause = msgCause;
                return this.self();
            }

            protected abstract T self();
            public abstract DomainException build();
    }
}
