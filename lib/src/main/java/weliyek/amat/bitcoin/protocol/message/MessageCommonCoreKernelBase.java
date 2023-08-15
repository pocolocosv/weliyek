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
package weliyek.amat.bitcoin.protocol.message;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.bitcoin.protocol.BitcoinMessageMagic;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolName;
import weliyek.bitcoin.BitcoinCommand;
import weliyek.ketza.base.composite.commissionable.CommissionableCoreKernel;

public class MessageCommonCoreKernelBase
        implements MessageUnpackedCommon,
                   CommissionableCoreKernel
{

    public final MessageUnpackedCore<?, ?, ?> ownerCore;

    final AmatNamespace magicNamespace;

    final AmatNamespace commandNamespace;

    private BitcoinMessageMagic magic;

    private BitcoinCommand command;

    protected MessageCommonCoreKernelBase(MessageUnpackedCore<?, ?, ?> owner) {
        this.ownerCore = owner;
        this.magicNamespace = owner.namespace().newChildNamespace(BitcoinProtocolName.MESSAGE_MAGIC);
        this.commandNamespace = owner.namespace().newChildNamespace(BitcoinProtocolName.MESSAGE_COMMAND);
    }

    @Override
    public void decommission() {
        resetValues();
    }

    protected void resetValues() {
        this.magic = null;
        this.command = null;
    }

    void commissionWithMagicAndCommand(BitcoinMessageMagic magic, BitcoinCommand command) {
        this.magic = magic;
        this.command = command;
    }

    @Override
    public BitcoinMessageMagic magic() {
        return this.magic;
    }

    @Override
    public BitcoinCommand command() {
        return this.command;
    }

    @Override
    public void finilizeCommission() {
        // Nothing to do.
    }

    @Override
    public boolean isCommissioned() {
        return (null != magic) && (null != command);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((command == null) ? 0 : command.hashCode());
        result = prime * result + ((magic == null) ? 0 : magic.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof MessageCommonCoreKernelBase))
            return false;
        MessageCommonCoreKernelBase other = (MessageCommonCoreKernelBase) obj;
        if (command == null) {
            if (other.command != null)
                return false;
        } else if (!command.equals(other.command))
            return false;
        if (magic == null) {
            if (other.magic != null)
                return false;
        } else if (!magic.equals(other.magic))
            return false;
        return true;
    }

}
