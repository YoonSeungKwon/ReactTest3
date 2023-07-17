package yoon.test.reactTest3.exception.valid;
import jakarta.validation.GroupSequence;
import yoon.test.reactTest3.exception.valid.ValidationGroups.EmailNotNull;
import yoon.test.reactTest3.exception.valid.ValidationGroups.EmailNotFormat;

@GroupSequence({EmailNotNull.class, EmailNotFormat.class})
public interface EmailDuplicationValidationSequence {
}
