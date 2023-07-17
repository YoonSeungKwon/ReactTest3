package yoon.test.reactTest3.exception.valid;

import jakarta.validation.GroupSequence;
import yoon.test.reactTest3.exception.valid.ValidationGroups.EmailNotNull;
import yoon.test.reactTest3.exception.valid.ValidationGroups.EmailNotFormat;
import yoon.test.reactTest3.exception.valid.ValidationGroups.PasswordNotNull;

@GroupSequence({EmailNotNull.class, EmailNotFormat.class, PasswordNotNull.class})
public interface LoginValidationSequence {
}
