package yoon.test.reactTest3.exception.valid;

import jakarta.validation.GroupSequence;
import yoon.test.reactTest3.exception.valid.ValidationGroups.TitleNotNull;
import yoon.test.reactTest3.exception.valid.ValidationGroups.ContentNotNull;

@GroupSequence({TitleNotNull.class, ContentNotNull.class})
public interface PostValidationSequence {
}
