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
package weliyek.amat.bitcoin.protocol.block;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.bitcoin.protocol.BitcoinOutpointNamespace;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolName;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolScriptNamespaces;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolSegregatedWitnessNamespace;

public class BitcoinProtocolTxInNamespaces
        extends AmatNamespace
{

    public final BitcoinOutpointNamespace outpoint;
    public final BitcoinProtocolScriptNamespaces script;
    public final BitcoinProtocolSegregatedWitnessNamespace segregatedWitness;
    public final AmatNamespace sequence;

    protected BitcoinProtocolTxInNamespaces(AmatNamespace parentNamespace) {
        super(parentNamespace, BitcoinProtocolName.TXIN, parentNamespace.factory());
        outpoint          = new BitcoinOutpointNamespace(this);
        script            = new BitcoinProtocolScriptNamespaces(this);
        segregatedWitness = new BitcoinProtocolSegregatedWitnessNamespace(this);
        sequence          = newChildNamespace(BitcoinProtocolName.TXIN_SEQUENCE);
    }

}
