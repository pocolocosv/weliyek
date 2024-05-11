/*
 * Weliyek Java Library
 * Copyright (C) 2023  Ricardo Villalobos - All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package weliyek.serialization;

public class WkSerdeDtreeNodeDataOperationException extends WkSerdeDtreeNodeDataComponentException
{

    public enum Explanation {
        UNEXPLAINED,
        INVALID_SETTINGS
    }

    private static final long serialVersionUID = 2019_05_18_000L;

    private final WkSerdeDtreeMsgOperation<?,?,?,?> operation;
    private final Explanation explanation;

    public WkSerdeDtreeNodeDataOperationException(WkSerdeDtreeMsgOperation<?,?,?,?> operation) {
        super(operation.parentField());
        this.operation = operation;
        this.explanation = Explanation.UNEXPLAINED;
    }

    public WkSerdeDtreeNodeDataOperationException(WkSerdeDtreeMsgOperation<?,?,?,?> operation, Explanation explanation) {
        super(operation.parentField());
        this.operation = operation;
        this.explanation = explanation;
    }

    public WkSerdeDtreeNodeDataOperationException(WkSerdeDtreeMsgOperation<?,?,?,?> operation, String message) {
        super(operation.parentField(), message);
        this.operation = operation;
        this.explanation = Explanation.UNEXPLAINED;
    }

    public WkSerdeDtreeNodeDataOperationException(WkSerdeDtreeMsgOperation<?,?,?,?> operation, Explanation explanation, String message) {
        super(operation.parentField(), message);
        this.operation = operation;
        this.explanation = explanation;
    }

    public WkSerdeDtreeNodeDataOperationException(WkSerdeDtreeMsgOperation<?,?,?,?> operation, Throwable cause) {
        super(operation.parentField(), cause);
        this.operation = operation;
        this.explanation = Explanation.UNEXPLAINED;
    }

    public WkSerdeDtreeNodeDataOperationException(WkSerdeDtreeMsgOperation<?,?,?,?> operation, String message, Throwable cause) {
        super(operation.parentField(), message, cause);
        this.operation = operation;
        this.explanation = Explanation.UNEXPLAINED;
    }

    public WkSerdeDtreeNodeDataOperationException(
        WkSerdeDtreeMsgOperation<?,?,?,?> operation,
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace) {
        super(operation.parentField(), message, cause, enableSuppression, writableStackTrace);
        this.operation = operation;
        this.explanation = Explanation.UNEXPLAINED;
    }

    public WkSerdeDtreeMsgOperation<?,?,?,?> getOperation() {
        return this.operation;
    }

    public Explanation getExplanation() {
        return this.explanation;
    }

}
