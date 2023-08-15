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
package weliyek.amat.bitcoin.protocol.message.payload;

import weliyek.amat.bitcoin.protocol.message.MessagePayloadIdentity;
import weliyek.amat.bitcoin.protocol.message.MessageUnpackedCommon;
import weliyek.amat.bitcoin.protocol.message.MessageUnpackedCore;
import weliyek.ketza.base.composite.commissionable.CommissionableBody;
import weliyek.ketza.base.composite.commissionable.CommissionableCoreKernel;
import weliyek.ketza.base.composite.commissionable.DecommissionableCore;

public interface PayloadCoreKernel<K extends PayloadCoreKernel<?>>
        extends CommissionableBody,
                DecommissionableCore<K>,
                CommissionableCoreKernel,
                MessagePayloadIdentity, // Methods used to identify the payload nature.
                MessageUnpackedCommon   // Provide common data with the payload object for ease of access.

{

    void commissionWithOwnerCore(MessageUnpackedCore<?, ?, ?> ownerCore);

    MessageUnpackedCore<?, ?, ?> ownerCore();

}
