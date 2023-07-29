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
package weliyek.amat.base;

public class OperationException extends PacketException
{

    public enum Explanation {
        UNEXPLAINED,
        INVALID_SETTINGS
    }

    private static final long serialVersionUID = 2019_05_18_000L;

    private final OperationSegment<?,?,?,?,?> operation;
    private final Explanation explanation;

    public OperationException(OperationSegment<?,?,?,?,?> operation) {
        super(operation.packetField());
        this.operation = operation;
        this.explanation = Explanation.UNEXPLAINED;
    }

    public OperationException(OperationSegment<?,?,?,?,?> operation, Explanation explanation) {
        super(operation.packetField());
        this.operation = operation;
        this.explanation = explanation;
    }

    public OperationException(OperationSegment<?,?,?,?,?> operation, String message) {
        super(operation.packetField(), message);
        this.operation = operation;
        this.explanation = Explanation.UNEXPLAINED;
    }

    public OperationException(OperationSegment<?,?,?,?,?> operation, Explanation explanation, String message) {
        super(operation.packetField(), message);
        this.operation = operation;
        this.explanation = explanation;
    }

    public OperationException(OperationSegment<?,?,?,?,?> operation, Throwable cause) {
        super(operation.packetField(), cause);
        this.operation = operation;
        this.explanation = Explanation.UNEXPLAINED;
    }

    public OperationException(OperationSegment<?,?,?,?,?> operation, String message, Throwable cause) {
        super(operation.packetField(), message, cause);
        this.operation = operation;
        this.explanation = Explanation.UNEXPLAINED;
    }

    public OperationException(
        OperationSegment<?,?,?,?,?> operation,
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace) {
        super(operation.packetField(), message, cause, enableSuppression, writableStackTrace);
        this.operation = operation;
        this.explanation = Explanation.UNEXPLAINED;
    }

    public OperationSegment<?,?,?,?,?> getRecord() {
        return this.operation;
    }

    public Explanation getExplanation() {
        return this.explanation;
    }

}
