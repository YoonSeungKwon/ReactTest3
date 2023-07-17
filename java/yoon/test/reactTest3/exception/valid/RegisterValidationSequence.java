package yoon.test.reactTest3.exception.valid;
import yoon.test.reactTest3.exception.valid.ValidationGroups.EmailNotNull;
import yoon.test.reactTest3.exception.valid.ValidationGroups.EmailNotFormat;
import yoon.test.reactTest3.exception.valid.ValidationGroups.NameNotNull;
import yoon.test.reactTest3.exception.valid.ValidationGroups.PasswordNotNull;

import jakarta.validation.GroupSequence;
@GroupSequence({EmailNotNull.class, EmailNotFormat.class, PasswordNotNull.class, NameNotNull.class})
public interface RegisterValidationSequence {
}
