package yoon.test.reactTest3.exception;
import yoon.test.reactTest3.exception.RegisterValidationGroups.EmailNotNull;
import yoon.test.reactTest3.exception.RegisterValidationGroups.EmailNotFormat;
import yoon.test.reactTest3.exception.RegisterValidationGroups.NameNotNull;
import yoon.test.reactTest3.exception.RegisterValidationGroups.PasswordNotNull;

import jakarta.validation.GroupSequence;
@GroupSequence({EmailNotNull.class, EmailNotFormat.class, PasswordNotNull.class, NameNotNull.class})
public interface RegisterValidationSequence {
}
