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
package weliyek.bitcoin;

import java.util.Optional;

import weliyek.amat2.protocol.field.MessageContext;

public class BitcoinConfig implements MessageContext
{

    private final BitcoinProtocolVersion version;

    private WkBitcoinCommandName previousSentCommand;
    private Optional<WkBitcoinCommandName> currentTxCommand;

    public static BitcoinConfig newConfig(BitcoinProtocolVersion version) {
        return new BitcoinConfig(version);
    }

    public static BitcoinConfig newConfig(int version) {
        return new BitcoinConfig(new BitcoinProtocolVersion(version));
    }

    public static BitcoinConfig newWithLatestVersion() {
        return new BitcoinConfig(BitcoinProtocolVersion.LATEST);
    }

    private BitcoinConfig(BitcoinProtocolVersion version) {
        this.version = version;
    }

    public BitcoinProtocolVersion latestSupportedVersion() {
        return version;
    }

    public BitcoinProtocolVersion effectiveVersion() {
        return latestSupportedVersion();
    }

    public Optional<WkBitcoinCommandName> previousSentCommand() {
        return Optional.ofNullable(this.previousSentCommand);
    }

    void setPreviousSentMessage(WkBitcoinCommandName roCmd) {
        if (null != roCmd) {
            throw new IllegalArgumentException();
        }
        this.previousSentCommand = roCmd;
    }

    public Optional<WkBitcoinCommandName> currentTxCommand() {
        return this.currentTxCommand;
    }

    void setCurrentTxCommand(WkBitcoinCommandName cmd) {
        this.currentTxCommand = Optional.ofNullable(cmd);
    }

}
