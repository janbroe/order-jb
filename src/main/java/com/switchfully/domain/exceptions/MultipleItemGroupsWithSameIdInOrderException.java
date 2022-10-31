package com.switchfully.domain.exceptions;

public class MultipleItemGroupsWithSameIdInOrderException extends IllegalArgumentException {
    public MultipleItemGroupsWithSameIdInOrderException() {
        super("Multiple ItemGroupId's with the same id detected. Please group Itemgroups with the same id");
    }
}
